//csrf토큰
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
//아임포트 결제
function import_inicis(e) {
    // 유효성 검사
    if ($('#jibunAddress').val() == "") {
        alert("지번 주소를 입력해 주십시오.");
        return false;
    }
    if ($('#roadAddress').val() == "") {
        alert("도로명 주소를 입력해 주십시오.");
        return false;
    }
    var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    var PhoneNum = $('#buyerTel1').val() + $('#buyerTel2').val() + $('#buyerTel3').val();

    if (regPhone.test(PhoneNum) == false) {
        alert("휴대폰 번호를 정확하게 입력해 주십시오.");
        return false;
    }
    // 따옴표 제거
    var prodKrwStr = $('#prodKrw').val();
    let prodKrw = prodKrwStr.replace(/[^\d]+/g, "");
    // 아임포트
    var IMP = window.IMP; // 생략가능
    IMP.init('imp64194502'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    var msg;

    // 요청 결제 정보
    IMP.request_pay({
        pg: 'html5_inicis',
        pay_method: $(e).attr('id'),
        merchant_uid: 'merchant_' + new Date().getTime(),
        buyer_name: $('#userName').val(),
        name: $('#prodName').val(),
        amount: prodKrw * $('#quantity').val(),
        buyer_tel: $('#buyerTel1').val() + $('#buyerTel2').val() + $('#buyerTel3').val(),
        buyer_addr: $('#roadAddress').val() + " " + $('#detailAddress').val(),
        buyer_email: 'Enter e-mail'
        // m_redirect_url : 'http://www.naver.com'
    }, function(rsp) {
        console.log("rsp상태:" + rsp.success);
        console.log("rsp:" + JSON.stringify(rsp));
        var serial_data = {
            imp_uid: rsp.imp_uid,
            quantity: $('#quantity').val(),
            buyer_tel: $('#buyerTel1').val() + $('#buyerTel2').val() + $('#buyerTel3').val(),
            prodId: $("#id").text()
        };

        if (rsp.success) {
            // [1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
            $.ajax({
                url: "/payments/complete", // cross-domain error가 발생하지 않도록 주의해주세요
                beforeSend: function(xhr) {
                    /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader(header, token);
                },
                type: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(serial_data)
            }).done(function(info) {
                console.log("결제 정보:" + JSON.stringify(info));
                // [2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                msg = '결제가 완료되었습니다.';
                // msg += '\n고유ID : ' + info.imp_uid;
                // msg += '\n상점 거래ID : ' + info.merchant_uid;
                // msg += '\결제 금액 : ' + info.paid_amount;
                // msg += '카드 승인번호 : ' + info.apply_num;
                alert(msg);
                // 성공시 이동할 페이지
                location.href = "/auth/product";
            }).fail(function(data, status, errorThrown) {
                console.log("오류명: " + errorThrown + "<br>" + "상태: " + status);
            });
        } else {
            msg = '결제에 실패하였습니다.\n';
            msg += '에러내용 : ' + rsp.error_msg;
            // 실패시 이동할 페이지
            // location.href="/auth/product";
            alert(msg);
        }
    })
};

//아임포트 결제 취소
function cancelPay(merchant_uid, cancel_request_amount) {
    var reason = prompt("결제 취소 사유를 작성해주세요");
    console.log(reason);

    $.ajax({
        url: "/payments/cancel", // 예: http://www.myservice.com/payments/cancel
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "merchant_uid": merchant_uid, // 예: ORD20180131-0000011
            "cancel_request_amount": cancel_request_amount, // 환불금액
            "reason": reason // 환불사유 
            /* to-do
             * "refund_holder": "홍길동", // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
            "refund_bank": "88" // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
            "refund_account": "56211105948400" // [가상계좌 환불시 필수입력] 환불 수령계좌 번호 
            */
        }),
        beforeSend: function(xhr) {
            /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
            xhr.setRequestHeader(header, token);
        },
        dataType: "json"
    }).done(function(info) {
    	console.log(JSON.stringify(info));
    	if(info.code == 0) {
    		msg = '취소가 완료되었습니다.';
    	}
    	else {
    		msg = JSON.stringify(info.message);
    	} 
        alert(msg);
        location.reload();
    }).fail(function(data, status, errorThrown) {
        console.log("오류명: " + errorThrown + "<br>" + "상태: " + status);
    });
}