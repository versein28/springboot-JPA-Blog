<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="container" style="width: 500px; float: left;">
		<span class="m-1"><h5>공지사항</h5></span>
		<c:forEach var="board" items="${boards.content}">
			<div class="card mb-3">
				<div class="card-body">
					<p class="card-title">${board.title}</p>
					<a href="/board/${board.id}" class="btn btn-light">상세보기</a>
				</div>
			</div>
		</c:forEach>

		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${boards.last}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
		<%-- 	//방문자 차트
	<div style="margin:auto;width:800px;">
		<canvas id="line-chart" class="mb-3 ml-3" width="800" height="370"></canvas>
	</div> --%>
	</div>

	<div class="container" style="width: 500px; float: left;">
		<span class="m-1"><h5>실시간 랭킹</h5></span>
		<ul id="rank" style="width: 200px; height: 30px;">
			<li value="서랍">서랍</li>
			<li value="의자">의자</li>
			<li value="침대">침대</li>
			<li value="쇼파">쇼파</li>
			<li value="테이블,탁자">테이블</li>
		</ul>
		<ul class="image-gallery">
<!-- 			<li><a>1위</a> <img src="https://source.unsplash.com/VWcPlbHglYc/640x416" alt="" />
				<div class="overlay">
					<span>Image title</span>
				</div></li> -->
		</ul>
	</div>

</div>

</section>
<script type="text/javascript">
	/* 	$(document).ready(function() {
	 getGraph();
	 });

	 function getGraph() {
	 let timeList = [];
	 let posList = [];
	 $.ajax({
	 url: "/auth/VisitInfo",
	 type: "GET",
	 dataType: "json",
	 success: function(data) {

	 // 그래프로 나타낼 자료 리스트에 담기
	 for (let i = 0; i < data.length; i++) {
	 timeList.push(data[i].date); //전체 날짜 리스트
	 }
	
	 // 중복된 날짜 제거
	 let date = timeList.filter((v, i) => timeList.indexOf(v) === i);

	 // foreach 구문을 이용하여 JSON 객체의 키(key)와 값(value)를 쉽게 가져올 수 있다
	 let result = {}; // json
	 timeList.forEach((x) => {
	 result[x] = (result[x] || 0) + 1;
	 });

	 // json형태 배열로 값 추출
	 let count = []; // array
	 var keys = Object.keys(result); 
	 for (var i = 0; i < keys.length; i++) {
	 var key = keys[i];
	 //console.log("key : " + key + ", value : " + result[key]);
	 count.push(result[key]);
	 }
	 //console.log(date);
	 //console.log(count); 

	 // 그래프
	 new Chart(document.getElementById("line-chart"), {
	 type: 'line',
	 data: {
	 labels: date, // X축 
	 datasets: [{
	 data: count, // 값
	 label: "방문자수",
	 borderColor: "#3e95cd",
	 fill: false
	 }]
	 },
	 options: {
	 responsive: false,
	 title: {
	 display: true,
	 text: '주간 방문자수'
	 }
	 }
	 }); //그래프
	 },
	 error: function() {
	 alert("실패");
	 }

	 }) // ajax	  
	 } // getGraph */
</script>

<script src="/js/product.js"></script>
<%@ include file="layout/footer.jsp"%>