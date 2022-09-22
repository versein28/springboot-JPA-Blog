<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="mb-3 text-center">
	<h5>회원정보</h5>
</div>
<div class="container">
	<input type="hidden" id="id" value="${principal.user.id}" />
	<div class="form-group">
		<label for="username">Username</label> <input type="text"
			value="${principal.user.username}" class="form-control"
			placeholder="Enter username" id="username" readonly>
	</div>

	<c:if test="${empty principal.user.oauth}">
		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" placeholder="Enter password" id="password">
		</div>
	</c:if>

	<c:choose>
		<c:when test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="email">Email</label> <input type="email"
					value="${principal.user.email}" class="form-control"
					placeholder="Enter email" id="email">
			</div>
		</c:when>
		<c:otherwise>
			<div class="form-group">
				<label for="email">Email</label> <input type="email"
					value="${principal.user.email}" class="form-control"
					placeholder="Enter email" id="email" readonly>
			</div>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty principal.user.oauth}">
			<button id="btn-update" class="btn btn-light">회원정보수정</button>
			<button id="btn-remove" class="btn btn-danger">회원탈퇴</button>
		</c:when>
		<c:otherwise>
			<button id="btn-update" class="btn btn-light" disabled>회원정보수정</button>
			<button id="btn-remove" class="btn btn-danger" disabled>회원탈퇴</button>
		</c:otherwise>
	</c:choose>
</div>
</section>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>