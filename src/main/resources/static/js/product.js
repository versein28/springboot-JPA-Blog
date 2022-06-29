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
			
			$.ajax({ 
				type: "POST",
				url: "/api/product",
				data: new FormData($("#update-form")[0]),
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