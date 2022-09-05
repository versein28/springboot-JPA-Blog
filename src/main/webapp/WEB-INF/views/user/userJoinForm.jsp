<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width:500px">
	<h3>개인회원 가입</h3>
	<form>
	<input id="role" type="hidden" value="USER">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username" style="">
			<input id="user-check" class="btn btn-light mt-3" value="중복확인" style="width:100px;height: 30px; line-height: 13px">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>	
		
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
			
	</form>
	<button id="btn-save" class="btn btn-light" style="margin-bottom:15px;" disabled>회원가입 완료</button>

</div>
</section>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


