<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width:500px">
	<h3>사업자 회원가입</h3>
	<form>
		<input id="role" type="hidden" value="SELLER">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>

	<div class="form-row">
		<input type="hidden" id="bt" value="사업자 타입">
		<div class="form-group col-md-6">
			<label for="b_no">business license</label> <input style="width:200px" type="text" class="form-control" id="b_no" placeholder="Enter business license" maxlength="10">
		</div>
		<div>
			<button id="bl-check" class="btn btn-light" style="margin-top: 32px;">사업자 등록상태 조회</button>
		</div>
	</div>

	<button id="btn-save" class="btn btn-light" style="margin-bottom: 15px;" disabled>회원가입 완료</button>

</div>
</section>
<script src="/js/bl.js"></script>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>