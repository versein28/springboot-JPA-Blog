<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form id="update-form">
		<input id="id" type="hidden" value="${product.id}">

		<div class="form-group">
			<label for="prodName">Name</label> <input type="text" class="form-control" placeholder="Enter Name" id="prodName" name="prodName" value="${product.prodName}">
		</div>

		<div class="form-group">
			<label for="prodBrand">Brand</label> <input type="text" class="form-control" placeholder="Enter Brand" id="prodBrand" name="prodBrand" value="${product.prodBrand}">
		</div>

		<div class="form-group">
			<label for="prodKrw">Krw</label> <input type="number" class="form-control" placeholder="Enter Krw" id="prodKrw" name="prodKrw" value="${product.prodKrw}">
		</div>

		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control summernote" rows="5" id="content" name="content">${product.content}</textarea>
		</div>

		<img src="${product.filePath}" style="width: 150px; height: auto;">
		<div class="custom-file" style="margin-bottom: 10px;">
			<input type="file" class="custom-file-input" id="file" name="file"> <label class="custom-file-label" for="file">Choose file</label>
		</div>

	</form>
	
	<button id="btn-update" type="button" class="btn btn-primary">상품수정 완료</button>
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 100
	});
</script>
<script src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>