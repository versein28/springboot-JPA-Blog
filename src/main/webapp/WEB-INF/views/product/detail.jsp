<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width: 500px;">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${product.user.id == principal.user.id}">
		<a href="/product/${product.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<img src="${product.filePath}" style="max-width: 100%; height: auto;" alt="" />
	<form>
		<input type="hidden" id="userName" value="${product.user.username}">
		<div class="form-group">
			글 번호 : <span id="id"><i>${product.id} </i></span> 작성자 : <span><i>${product.user.username} </i></span>
		</div>

		<div class="form-group">
			<label for="prodName">상품명</label> <input type="text" class="form-control" value="${product.prodName}" id="prodName" readonly>
		</div>

		<div class="form-group">
			<label for="prodBrand">브랜드명</label> <input type="text" class="form-control" value="${product.prodBrand}" id="prodBrand" readonly>
		</div>

		<div class="form-group">
			<label for="prodAddress">주소</label> <input type="text" class="form-control" placeholder="Enter Address" id="prodAddress">
		</div>

		<div class="form-group">
			<label for="prodAmount">수량</label> <input type="number" class="form-control" id="prodAmount" min="1" value="1">
		</div>

		<div class="form-group">
			<label for="prodKrw">가격</label> <input type="number" class="form-control" value="${product.prodKrw}" id="prodKrw" readonly>
		</div>
	</form>
	<div>
		<button style="border: none; background: transparent;" onclick="kakaoPay()">
			<img src="/image/payment_icon_yellow_small.png">
		</button>
	</div>
</div>

<script src="/js/import.js"></script>
<script src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>
