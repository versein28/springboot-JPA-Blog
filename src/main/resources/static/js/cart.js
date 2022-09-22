//csrf토큰
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
//장바구니 담기
$("#cart-save").on("click", function() {
	let data = {
		id: $("#id").text()
	};
	$.ajax({
		type: "POST",
		url: "/api/cart",
		data: JSON.stringify(data),
		beforeSend : function(xhr)
        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
        },
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(rsp) {
		if (rsp.status == 200) {
			alert("장바구니에 담기가 완료 되었습니다.");
		} else {
			alert(rsp.data);
		}
	}).fail(function(error) {
		alert(JSON.stringify(error));
	});
});

//장바구니 선택 삭제
$('#deleteCheckedItem').on("click", function() {
	var checkBoxArr = [];
	$("input:checkbox[name='checkbox']:checked").each(function() {
		checkBoxArr.push($(this).val()); // 체크된 것만 값을 뽑아서 배열에 push
		console.log(checkBoxArr);
	})
	$.ajax({
		type: "POST",
		url: "/api/cart/delete",
		beforeSend : function(xhr)
        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
        },
		data: {
			checkBoxArr: checkBoxArr
			// seq 값을 가지고 있음.
		}
	}).done(function(rsp) {
		if (rsp.status = 200) {
			alert("삭제가 완료 되었습니다.");
			location.reload();
		} else {
			alert(rsp.data);
		}
	}).fail(function(error) {
		alert(JSON.stringify(error));
	});
});

// 장바구니 수량 변경 
$(".orderCountModifyBtn").on("click", function() {
	let id = $(this).parent().find("#cartId").val();
	let data = {
		count: $(this).parent().find("#orderQtr").val()
	}
	$.ajax({
		type: "PUT",
		url: "/api/cart/" + id,
		data: JSON.stringify(data),
		beforeSend : function(xhr)
        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
        },
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(rsp) {
		if (rsp.status = 200) {
			alert("수량변경이 완료 되었습니다.");
			location.reload();
		} else {
			alert(rsp.data);
		}
	}).fail(function(error) {
		alert(JSON.stringify(error));
	});
});

// 장바구니 삭제 
$(".removeCartItemBtn").on("click", function() {
	if (confirm("장바구니 상품을 삭제하시겠습니까?") == true) { // 확인
		let id = $(this).parent().find("#cartId").val();
		$.ajax({
			type: 'DELETE',
			url: "/api/cart/" + id,
			beforeSend : function(xhr)
	        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(header, token);
	        },
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(rsp) {
			if (rsp.status = 200) {
				alert("장바구니 삭제가 완료 되었습니다.");
				location.reload();
			} else {
				alert(rsp.data);
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	} else { //취소
		return false;
	}
})
// 전체 체크박스 컨트롤 
$("#checkAll").click(function() {
	$('input:checkbox').not(this).prop('checked', this.checked);
});
// 전체 체크박스 끄기 
$("#checkOff").click(function() {
	$('input:checkbox').prop('checked', false);
});