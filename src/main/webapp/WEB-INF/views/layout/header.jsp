<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>쇼핑몰</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
    <link rel="stylesheet" href="/css/main.css" type="text/css">
    <!--  jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <!--  bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <!--  summernote -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <!--  chart.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <!-- datepicker 한국어로 -->
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>
    <!-- csrf 토큰 -->
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>

<body>
    <div id="wrap">
        <section>
            <nav class="navbar navbar-expand-md bg-dark navbar-dark mb-3">
	            <form id="logout-form" action="<c:url value="/logout"/>" method="post">
    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
                <a class="navbar-brand" href="/">쇼핑몰</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
                    <c:choose>
                        <c:when test="${empty principal}">
                            <ul class="navbar-nav">
                                <li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
                                <li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
                                <li class="nav-item"><a class="nav-link" href="/auth/product">상품 목록</a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul class="navbar-nav">
                                <li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick="document.getElementById('logout-form').submit();">로그아웃</a></li>
                                <li class="nav-item"><a class="nav-link" href="/auth/product">상품 목록</a></li>
                                <li class="nav-item"><a class="nav-link" href="/user/myPage">내 주문</a></li>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <li class="nav-item"><a class="nav-link" href="/board/saveForm">공지사항 쓰기</a></li>\
                                </sec:authorize>
                                <%-- <sec:authorize access="hasAnyRole('ADMIN','SELLER')"> --%>
                                    <li class="nav-item"><a class="nav-link" href="/product/saveForm">상품 등록하기</a></li>
                                <%-- </sec:authorize> --%>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </nav>