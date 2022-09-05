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
<script src="/js/kakaopay.js"></script>
<!-- datepicker 한국어로 -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>


<style>
.ui-datepicker {
	font-size: 12px;
	width: 190px;
}

.ui-datepicker select.ui-datepicker-month {
	width: 40%;
	font-size: 11px;
}

.ui-datepicker select.ui-datepicker-year {
	width: 50%;
	font-size: 11px;
}

h5 {
	font-weight: bold;
}

html {
	font-size: 14px;
}

/*datepicker에서 사용한 이미지 버튼 style적용*/
img.ui-datepicker-trigger {
	margin-left: 5px;
	vertical-align: middle;
	cursor: pointer;
}

* {
	margin: 0;
	padding: 0;
}

ul {
	list-style: none;
}

#rank li {
	float:left;
	margin-right:3px;
	color: #adb5bd;
}
#rank li:hover {
	font-weight: bold;
	cursor: pointer;
}

.image-gallery {
  display: flex;
}
.image-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  overflow: hidden;
}

.image-gallery > li {
  flex-basis: 150px; /* width: 350px; */
  float:left;
}

.image-gallery li img {
  object-fit: cover;
  width: 100%;
  height: 100%;
  vertical-align: middle;
  border-radius: 5px;
}
.image-gallery::after {
  content: "";
  flex-basis: 350px;
}
.image-gallery > li {
  /* ... */
  position: relative;
  cursor: pointer;
}
.overlay {
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(57, 57, 57, 0.502);
  top: 0;
  left: 0;
  transform: scale(0);
  transition: all 0.2s 0.1s ease-in-out;
  color: #fff;
  border-radius: 5px;
  /* center overlay text */
  display: flex;
  align-items: center;
  justify-content: center;
}

/* hover */
.image-gallery li:hover .overlay {
  transform: scale(1);
}
#wrap {
	min-height: 100vh;
	position: relative;
	width: 100%;
}

section {
	padding-bottom: 110px;
}

footer {
	width: 100%;
	height: 110px;
	bottom: 0px;
	position: absolute;
	border-top: 1px solid #c4c4c4;
	padding-top: 15px;
	color: #808080;
	font-size: 11px;
}

footer a {
	display: inline-block;
	margin: 0 20px 10px 20px;
	color: #808080;
	font-size: 11px;
}

footer a:visited {
	color: #808080;
}

footer p {
	margin-top: 0;
	margin-bottom: 0;
}

footer p span {
	display: inline-block;
	margin-left: 20px;
}

.orderForm input {
	margin-bottom: 5px;
}

.orderContainer {
	width: 100%;
	margin: 30px auto !important;
}

.orderContainer>div {
	margin-left: 30px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div id="wrap">
		<section>
			<nav class="navbar navbar-expand-md bg-dark navbar-dark mb-3">
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
								<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
								<li class="nav-item"><a class="nav-link" href="/auth/product">상품 목록</a></li>
								<li class="nav-item"><a class="nav-link" href="/user/myPage">내 주문</a></li>
								<sec:authorize access="hasRole('ADMIN')">
									<li class="nav-item"><a class="nav-link" href="/board/saveForm">공지사항 쓰기</a></li>\
								</sec:authorize>
								<sec:authorize access="hasAnyRole('ADMIN','SELLER')">
									<li class="nav-item"><a class="nav-link" href="/product/saveForm">상품 등록하기</a></li>
								</sec:authorize>
							</ul>
						</c:otherwise>
					</c:choose>

				</div>
			</nav>