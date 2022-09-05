<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mb-3" style="width: 700px; height: 250px; margin: 0 auto;">
	<div class="card mb-3" style="width: 300px;">
		<div class="card-header">내 정보</div>
		<div class="card-body">
			<p class="card-title">닉네임: ${payments.content[0].user.username}</p>
			<p class="card-text">이메일: ${payments.content[0].user.email}</p>
			<a href="/user/updateForm" class="btn btn-light">회원 정보</a>
		</div>
	</div>
	<a href="/user/myPage" class="btn btn-outline-primary mr-3" style="float: left;">주문 내역 조회</a> 
	<a href="/user/myCart" class="btn btn-outline-primary" style="float: left;">내 장바구니</a>
</div>
<div class="container" style="width: 700px;">
	<h5>주문내역 조회</h5>
	<div style="float: right">
		<form id="datePickerForm" action="/user/myPage" method="GET" onsubmit="return validateForm()">
			<label for="fromDate" style="font-size: 13px;">시작일</label> <input type="text" name="fromDate" id="fromDate" value="${param.fromDate}" style="width: 100px; display: inline"> ~ <label
				for="toDate" style="font-size: 13px;">종료일</label> <input type="text" name="toDate" id="toDate" value="${param.toDate}" style="width: 100px; display: inline"> <input class="btn btn-light"
				type="submit" value="검색하기" style="height: 30px; line-height: 13px">
		</form>
	</div>
	<table class="table" style="table-layout: fixed; background-color: white">
		<thead>
			<th width="20%">이미지</th>
			<th>상품정보</th>
			<th>주문일자</th>
			<th>주문번호</th>
			<th>주문금액(수량)</th>
			<th>주문상태</th>
		</thead>
		<c:forEach var="payment" items="${payments.content}">
			<tbody>
				<tr>
					<td><img style="max-width: 100%; height: auto;" src="${payment.product.filePath}" class="itemImage" alt=""></td>
					<td><a style="color: gray">${payment.product.prodBrand}</a>
						<p>${payment.product.prodName}</p></td>
					<td><fmt:formatDate value="${payment.order_date}" pattern="yyyy-MM-dd" /></td>
					<td>
						<p>${payment.id}</p>
					</td>
					<td><fmt:formatNumber value="${payment.amount}" pattern="#,###" /> <br> <a style="color: gray">${payment.qty}</a></td>
					<td>준비중</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>

	<ul class="pagination justify-content-center" style="margin-top: 20px;">
		<c:choose>
			<c:when test="${payments.first}">
				<li class="page-item disabled"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${payments.last}">
				<li class="page-item disabled"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${payments.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
</section>
<script>
	//datepicker 한국어로 사용하기 위한 언어설정
	$.datepicker.setDefaults($.datepicker.regional['ko']);

	// 시작일(fromDate)은 종료일(toDate) 이후 날짜 선택 불가
	// 종료일(toDate)은 시작일(fromDate) 이전 날짜 선택 불가

	//시작일.
	$('#fromDate').datepicker({
		autoSize : true,
		showOn : "both", // 달력을 표시할 타이밍 (both: focus or button)
		buttonImage : "/image/calender.png", // 버튼 이미지
		buttonImageOnly : true, // 버튼 이미지만 표시할지 여부
		buttonText : "날짜선택", // 버튼의 대체 텍스트
		dateFormat : "yy-mm-dd", // 날짜의 형식
		changeMonth : true, // 월을 이동하기 위한 선택상자 표시여부
		//minDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
		onClose : function(selectedDate) {
			// 시작일(fromDate) datepicker가 닫힐때
			// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
			$("#toDate").datepicker("option", "minDate", selectedDate);
		}
	});

	//종료일
	$('#toDate').datepicker({
		autoSize : true,
		showOn : "both",
		buttonImage : "/image/calender.png",
		buttonImageOnly : true,
		buttonText : "날짜선택",
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		//minDate: 0, // 오늘 이전 날짜 선택 불가
		onClose : function(selectedDate) {
			// 종료일(toDate) datepicker가 닫힐때
			// 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
			$("#fromDate").datepicker("option", "maxDate", selectedDate);
		}
	});

	//datepicker validation
	function validateForm() {
		if ($("#fromDate").val() == "") {
			alert("시작일자를 선택해주세요");
			return false;
		}
		if ($("#toDate").val() == "") {
			alert("종료일자를 선택해주세요");
			return false;
		}
	}
</script>
<script src="/js/cart.js"></script>
<%@ include file="../layout/footer.jsp"%>