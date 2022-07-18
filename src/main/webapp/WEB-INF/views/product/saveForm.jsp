<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form id="update-form">
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
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content" name="content"></textarea>
		</div>

		<div class="custom-file" style="margin-bottom: 10px;">
			<input type="file" class="custom-file-input" id="file" name="file"> <label class="custom-file-label" for="file">Choose file</label>
		</div>
	</form>
	<button id="btn-save" type="button" class="btn btn-primary">상품등록 완료</button>
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 100
	});
    function inputNumberAutoComma(obj) {
       
        // 콤마( , )의 경우도 문자로 인식되기때문에 콤마를 따로 제거한다.
        var deleteComma = obj.value.replace(/\,/g, "");

        // 콤마( , )를 제외하고 문자가 입력되었는지를 확인한다.
        if(isFinite(deleteComma) == false) {
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
</script>
<script src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>