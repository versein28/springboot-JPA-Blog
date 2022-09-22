<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mb-3 text-center">
    <h3>회원가입</h3>
</div>
<div class="container-fluid mt-5">
    <div class="row justify-content-center">
        <div id="join-individual" class="card bg-transparent mb-3 mr-3" onclick="UserJoin()">
            <div class="card-header bg-light pl-4">개인회원</div>
            <div class="card-body">
                <b class="card-title p-3">개인회원</b>
                <p class="card-text p-3">이름,이메일 등 간단한 개인정보를 입력해주세요.</p>
            </div>
        </div>
        <div id="join-buisness" class="card bg-transparent mb-3" onclick="SellerJoin()">
            <div class="card-header bg-light pl-4">사업자</div>
            <div class="card-body">
                <b class="card-title p-3">사업자</b>
                <p class="card-text p-3">사업자번호와 카테고리등의 추가작성이 필요합니다.</p>
            </div>
        </div>
    </div>
</div>
</section>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>