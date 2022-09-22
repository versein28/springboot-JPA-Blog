//datepicker 한국어로 사용하기 위한 언어설정
$.datepicker.setDefaults($.datepicker.regional['ko']);

// 시작일(fromDate)은 종료일(toDate) 이후 날짜 선택 불가
// 종료일(toDate)은 시작일(fromDate) 이전 날짜 선택 불가

// 시작일.
$('#fromDate').datepicker({
	autoSize : true,
	showOn : "both", // 달력을 표시할 타이밍 (both: focus or button)
	buttonImage : "/image/calender.png", // 버튼 이미지
	buttonImageOnly : true, // 버튼 이미지만 표시할지 여부
	buttonText : "날짜선택", // 버튼의 대체 텍스트
	dateFormat : "yy-mm-dd", // 날짜의 형식
	changeMonth : true, // 월을 이동하기 위한 선택상자 표시여부
	// minDate: 0, // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
	onClose : function(selectedDate) {
		// 시작일(fromDate) datepicker가 닫힐때
		// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
		$("#toDate").datepicker("option", "minDate", selectedDate);
	}
});

// 종료일
$('#toDate').datepicker({
	autoSize : true,
	showOn : "both",
	buttonImage : "/image/calender.png",
	buttonImageOnly : true,
	buttonText : "날짜선택",
	dateFormat : "yy-mm-dd",
	changeMonth : true,
	// minDate: 0, // 오늘 이전 날짜 선택 불가
	onClose : function(selectedDate) {
		// 종료일(toDate) datepicker가 닫힐때
		// 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정
		$("#fromDate").datepicker("option", "maxDate", selectedDate);
	}
});

// datepicker validation
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