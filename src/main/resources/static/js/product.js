//csrf토큰
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
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
			// 유효성 검사
			if ($('#prodName').val() == "") {
	            alert("상품이름을 입력해 주십시오.");
	            return false;
	        }    
	        if ($('#prodBrand').val() == "") {
	            alert("브랜드명을 입력해 주십시오.");
	            return false;
	        }
	        if ($('#prodKrw').val() == "") {
	            alert("가격을 입력해 주십시오.");
	            return false;
	        }
	        if ($('#qty').val() == "") {
	            alert("수량을 입력해 주십시오.");
	            return false;
	        }
			// 따옴표 제거
			var formData = new FormData($("#save-form")[0]);
			var prodKrwStr = formData.get('prodKrw');
			let prodKrw = prodKrwStr.replace(/[^\d]+/g, "");
			formData.set('prodKrw', prodKrw);
			
			$.ajax({ 
				type: "POST",
				url: "/api/product",
				data: formData /* new FormData($("#save-form")[0]) */,
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        },
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
				url: "/api/product/"+id,
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        }
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
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        },
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