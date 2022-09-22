<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../layout/header.jsp"%>

<div id="myPageContainer" class="container-fluid">
    <div class="container mb-3">
        <span class="m-1 text-center">
            <h5>내 정보</h5>
        </span>
        <div class="card mb-3">
            <div class="card-header">내 정보</div>
            <div class="card-body">
                <p class="card-title">닉네임: ${user.username}</p>
                <p class="card-text">이메일: ${user.email}</p>
                <a href="/user/updateForm" class="btn btn-light">회원 정보</a>
            </div>
        </div>
        <a href="/user/myPage" class="btn btn-outline-primary mr-3">주문 내역 조회</a>
        <a href="/user/myCart" class="btn btn-outline-primary">내 장바구니</a>
    </div>
    <div class="container cartBox">
        <span class="m-1 text-center">
            <h5>장바구니 조회</h5>
        </span>
        <table class="table">
            <thead class="text-center">
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
                        <td><input name="checkbox" class="checkbox" type="checkbox" value="${cart.id}"></td>
                        <td><img id="itemImage" src="${cart.filePath}" class="itemImage" alt=""></td>
                        <td>${cart.prodName}<br> <a>${cart.prodBrand}</a>
                        </td>
                        <td>
                            <fmt:formatNumber value="${cart.prodKrw}" pattern="#,###" />
                        </td>
                        <td>
                            <!--  ${cart.id} : cartProduct의 id --> <input class="form-control mb-1" id="orderQtr" class="orderQtr" value="${cart.count}"> <input id="cartId" class="cartId" type="hidden" value="${cart.id}">
                            <button class="btn btn-light orderCountModifyBtn">변경</button>
                        </td>
                        <td class="text-center"><input id="cartId" class="cartId" type="hidden" value="${cart.id}">
                            <button class="btn btn-light removeCartItemBtn">삭제하기</button></td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>

        <a id="deleteCheckedItem" class="btn btn-light">선택삭제</a> <a id="checkOff" class="btn btn-light">전체해제</a>

        <ul class="pagination justify-content-center mb-2">
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
</div>
</section>


<script src="/js/cart.js"></script>
<%@ include file="../layout/footer.jsp"%>