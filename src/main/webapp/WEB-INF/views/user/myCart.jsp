<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mb-3" style="width: 700px; height: 250px; margin: 0 auto;">
	<div class="card mb-3" style="width: 300px;">
		<div class="card-header">내 정보</div>
		<div class="card-body">
			<p class="card-title">닉네임: ${user.username}</p>
			<p class="card-text">이메일: ${user.email}</p>
			<a href="/user/updateForm" class="btn btn-light">회원 정보</a>
		</div>
	</div>
	<a href="/user/myPage" class="btn btn-outline-primary mr-3" style="float: left;">주문 내역 조회</a>
	<a href="/user/myCart" class="btn btn-outline-primary" style="float: left;">내 장바구니</a>
</div>
<div class="container" style="width: 700px;">
	<table class="table" style="table-layout: fixed; background-color: white">
		<thead>
			<th width="4%"><input id="checkAll" type="checkbox"></th>
			<th width="20%">이미지</th>
			<th>상품정보</th>
			<th>상품금액</th>
			<th>수량</th>
			<th>주문관리</th>
		</thead>
		<c:forEach var="cart" items="${carts.content}">
			<tbody>
				<tr>
					<td><input name="checkbox" type="checkbox" value="${cart.id}"></td>
					<td><img style="max-width: 100%; height: auto;" src="${cart.filePath}" class="itemImage" alt=""></td>
					<td>${cart.prodName}<br> <a style="color: gray">${cart.prodBrand}</a>
					</td>
					<td><fmt:formatNumber value="${cart.prodKrw}" pattern="#,###" /></td>
					<td>
						<!--  ${cart.id} : cartProduct의 id --> <input id="orderQtr" class="orderQtr" style="width: 100px" type="number" value="${cart.count}"> <input id="cartId" class="cartId" type="hidden"
						value="${cart.id}">
						<button class="btn btn-light orderCountModifyBtn">변경</button>
					</td>
					<td style="text-align: center; float: left"><input id="cartId" class="cartId" type="hidden" value="${cart.id}">
						<button class="btn btn-light removeCartItemBtn">삭제하기</button></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>

	<a id="deleteCheckedItem" class="btn btn-light">선택삭제</a> <a id="checkOff" class="btn btn-light">전체해제</a>

	<ul class="pagination justify-content-center" style="margin-top: 20px;">
		<c:choose>
			<c:when test="${carts.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${carts.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${carts.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${carts.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${carts.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${carts.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
</section>


<script src="/js/cart.js"></script>
<%@ include file="../layout/footer.jsp"%>