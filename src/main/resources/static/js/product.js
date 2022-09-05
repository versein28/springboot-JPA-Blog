//실시간 랭킹
$("#rank li").click(function() {
/*   var data = {
      content: $(this).attr("value")
   };*/
   var content = $(this).attr("value");
   
   $.ajax({
      type: "POST",
      url: "/api/product/rank",
      data: content,/*
      contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
      dataType: "json", // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게  json이라면) => javascript오브젝트로 변경
*/      success: function(result) {
        
            console.log("result"+result);
         
      },
      error: function(request, status, error) {
    	  console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
});

let product = {
		init: function(){
			$("#btn-save").on("click", ()=>{ 
				this.save();
			});
			$("#btn-delete").on("click", ()=>{ 
				this.deleteById();
			});
			$("#btn-update").on("click", ()=>{ 
				this.update();
			});
		},
		
		save: function(){
			// 따옴표 제거
			var formData = new FormData($("#save-form")[0]);
			var prodKrwStr = formData.get('prodKrw');
			let prodKrw = prodKrwStr.replace(/[^\d]+/g, "");
			formData.set('prodKrw', prodKrw);
			
			$.ajax({ 
				type: "POST",
				url: "/api/product",
				data: formData /* new FormData($("#save-form")[0]) */,
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false
			}).done(function(resp){
				alert("상품등록이 완료되었습니다.");
				location.href = "/auth/product";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		deleteById: function(){
			let id = $("#id").text();
			
			$.ajax({ 
				type: "DELETE",
				url: "/api/product/"+id
			}).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/auth/product";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		update: function(){
			let id = $("#id").val();
			// 파일 유효성 검사
			var file = $('#file').val();
			if(!file) {
				alert("파일을 첨부 해주세요");
				return false;
			}
			$.ajax({ 
				type: "POST",
				url: "/api/product/"+id,
			    data: new FormData($("#update-form")[0]),
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false
			}).done(function(resp){
				alert("글수정이 완료되었습니다.");
				location.href = "/auth/product";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
}

product.init();