<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container mt-5" style="text-align: center;">
	<div class="row justify-content-center">
		<div id="join1" class="card bg-transparent mb-3 mr-3 " style="max-width: 18rem;" onclick="UserJoin()">
			<div class="card-header bg-light">회원가입</div>
			<div class="card-body">
				<h5 class="card-title">개인회원</h5>
				<p class="card-text">이름,이메일 등 간단한 개인정보를 입력해주세요.</p>
			</div>
		</div>

		<div id="join2" class="card bg-transparent mb-3" style="max-width: 18rem;" onclick="SellerJoin()">
			<div class="card-header bg-light">회원가입</div>
			<div class="card-body">
				<h5 class="card-title">사업자</h5>
				<p class="card-text">사업자번호와 카테고리등의 추가작성이 필요합니다.</p>
			</div>
		</div>
	</div>
</div>
</section>
<script>
	$('#join1, #join2').hover(function(){
		$(this).css('cursor','pointer');
	}, function() {
		$(this).css('cursor','default');
	 });
	
	function UserJoin() {
		console.log("User!");
		location.href = "/auth/userJoinForm";
	}
	function SellerJoin() {
		console.log("Seller!");
		location.href = "/auth/sellerJoinForm";
	}
</script>
<%@ include file="../layout/footer.jsp"%>