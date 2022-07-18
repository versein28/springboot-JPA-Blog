<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="POST">
		<div class="form-group">
			<label for="username">Username</label> <input name="username" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input name="password" type="password" class="form-control" placeholder="${dev.value}Enter password" id="password">
		</div>
		<!-- prod https://www.hwsin.shop/auth/kakao/callback -->
		<!-- dev  http://localhost:8080/auth/kakao/callback -->
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=976c659784c035a34f0f977c860fe2a0&redirect_uri=https://www.hwsin.shop/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"></a>
		<button class="btn btn-success" type="button" onclick="location.href = '/auth/pwdSearchForm'">비밀번호 찾기</button>
	</form>
</div>

<!-- <script src="/js/user.js"></script> -->
<%@ include file="../layout/footer.jsp"%>


