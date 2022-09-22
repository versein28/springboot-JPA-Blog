<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="mb-3 text-center">
    <h3>비밀번호 찾기</h3>
</div>
<div class="container">
	<div class="form-group">
		<label for="email">Email</label> <input name="email" type="text" class="form-control" placeholder="Enter email" id="email">
	</div>

	<div class="form-group">
		<label for="username">Username</label> <input name="username" type="text" class="form-control" placeholder="Enter username" id="username">
	</div>
	<button id="btn-search" class="btn btn-light" type="button">제출하기</button>
</div>
</section>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


