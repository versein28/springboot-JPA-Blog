<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="email">Email</label> <input name="email" type="text" class="form-control" placeholder="Enter email" id="email">
		</div>

		<div class="form-group">
			<label for="username">Username</label> <input name="username" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
	</form>
		<button id="btn-search" class="btn btn-primary" type="button">제출하기</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


