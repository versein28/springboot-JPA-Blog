<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width:700px">
	<form id="save-form">
		<div class="form-group">
			<label for="prodName">Name</label> <input type="text" class="form-control" placeholder="Enter Name" id="prodName" name="prodName">
		</div>

		<div class="form-group">
			<label for="prodBrand">Brand</label> <input type="text" class="form-control" placeholder="Enter Brand" id="prodBrand" name="prodBrand">
		</div>

		<div class="form-group">
			<label for="prodKrw">Krw</label> <input type="text" onKeyup="inputNumberAutoComma(this);" class="form-control" placeholder="Enter Krw" id="prodKrw" name="prodKrw">
		</div>

		<div class="form-group">
			<label for="qty">quantity</label> <input type="number" class="form-control" placeholder="Enter quantity" id="qty" name="qty">
		</div>

		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content" name="content"></textarea>
		</div>

		<img id="preview" style="width: 150px; height: auto;">
		<div class="custom-file" style="margin-bottom: 10px;">
			<input type="file" class="custom-file-input ex_file" id="file" name="file" onchange="readURL(this);"> <label class="custom-file-label" for="file">Choose file</label>
		</div>
	</form>
	<button id="btn-save" type="button" class="btn btn-light">상품등록 완료</button>
</div>
</section>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 100
	});
	
	//쉼표 추가
	function inputNumberAutoComma(obj) {

		// 콤마( , )의 경우도 문자로 인식되기때문에 콤마를 따로 제거한다.
		var deleteComma = obj.value.replace(/\,/g, "");

		// 콤마( , )를 제외하고 문자가 입력되었는지를 확인한다.
		if (isFinite(deleteComma) == false) {
			alert("문자는 입력하실 수 없습니다.");
			obj.value = "";
			return false;
		}

		// 기존에 들어가있던 콤마( , )를 제거한 이 후의 입력값에 다시 콤마( , )를 삽입한다.
		obj.value = inputNumberWithComma(inputNumberRemoveComma(obj.value));
	}
	
	  // 천단위 이상의 숫자에 콤마( , )를 삽입하는 함수
    function inputNumberWithComma(str) {

        str = String(str);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
    }
	  
    // 콤마( , )가 들어간 값에 콤마를 제거하는 함수
    function inputNumberRemoveComma(str) {

        str = String(str);
        return str.replace(/[^\d]+/g, "");
    }
    
	//첨부파일
	function readURL(input) {
		//파일명 출력
		var fileInput = document.getElementsByClassName("ex_file");
		
		for (var i = 0; i < fileInput.length; i++) {
			if (fileInput[i].files.length > 0) {
				for (var j = 0; j < fileInput[i].files.length; j++) {
					$("label[for='file']").text(fileInput[i].files[j].name);
				}
			}
		}
		//미리보기
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			};
			reader.readAsDataURL(input.files[0]);
		} else {
			document.getElementById('preview').src = "";
		}
	}
</script>
<script src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>