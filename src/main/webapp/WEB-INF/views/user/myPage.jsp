<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
        <a href="/user/myPage" class="btn btn-outline-primary mr-3">주문 내역
            조회</a> <a href="/user/myCart" class="btn btn-outline-primary">내 장바구니</a>
    </div>

    <div class="container orderBox">
        <span class="m-1 text-center">
            <h5>주문 내역 조회</h5>
        </span>
        <form id="datePickerForm" class="mb-1" action="/user/myPage" method="GET" onsubmit="return validateForm()">
            <label for="fromDate">시작일</label> <input class="form-control" type="text" name="fromDate" id="fromDate" value="${param.fromDate}">
            ~ <label for="toDate">종료일</label> <input class="form-control" type="text" name="toDate" id="toDate" value="${param.toDate}">
            <input class="btn btn-light ml-3" type="submit" value="검색하기">
        </form>
 
        <table class="table">
            <thead>
                <th width="20%">이미지</th>
                <th>상품정보</th>
                <th>주문일자</th>
                <th>주문번호</th>
                <th>주문금액(수량)</th>
                <th>주문상태</th>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${payments.content}">
                    <tr>
                        <td><img id="itemImage" src="${payment.product.filePath}" class="itemImage" alt=""></td>
                        <td><a>${payment.product.prodBrand}</a>
                            <p>${payment.product.prodName}</p>
                        </td>
                        <td>
                            <fmt:formatDate value="${payment.orderDate}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>
                            <p>${payment.id}</p>
                        </td>
                        <td>
                            <fmt:formatNumber value="${payment.amount}" pattern="#,###" /> <br> <a>${payment.qty}</a></td>
                        <td><p>준비중</p>
                        	<button class="btn btn-danger" onclick="cancelPay('${payment.merchantUid}',${payment.amount})">결제 취소</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <ul class="pagination justify-content-center m-3">
            <c:choose>
                <c:when test="${payments.first}">
                    <li class="page-item disabled"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number-1}">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number-1}">Previous</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${payments.last}">
                    <li class="page-item disabled"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number+1}">Next</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number+1}">Next</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
</section>
<script src="/js/import.js"></script>
<script src="/js/myPage.js"></script>
<%@ include file="../layout/footer.jsp"%>