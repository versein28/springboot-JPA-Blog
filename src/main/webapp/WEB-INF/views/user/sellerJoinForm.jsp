<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="mb-3">
    	<h3>사업자 회원가입</h3>
    </div>
    <input id="role" type="hidden" value="SELLER"> <!-- role -->
    <input id="bt" type="hidden"> <!-- business type -->
    <div class="form-group">
        <label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username"> <input id="user-check" class="btn btn-light mt-3" value="중복확인">
    </div>

    <div class="form-group">
        <label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
    </div>

    <div class="form-group">
        <label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
    </div>

    <label for="b_no">business license</label>
    <div class="row">
        <div class="form-group col-md-6">
            <input type="text" class="form-control" id="b_no" placeholder="Enter business license" maxlength="10">
        </div>
        <div>
            <button id="bl-check" class="btn btn-light">사업자 등록상태 조회</button>
        </div>
    </div>

    <button id="btn-save" class="btn btn-light" disabled>회원가입 완료</button>
</div>
</section>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>