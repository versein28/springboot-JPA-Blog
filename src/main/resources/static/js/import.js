    function kakaoPay() {
        var IMP = window.IMP; // 생략가능
        IMP.init('imp64194502'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        //요청 결제 정보
        var prodName = $('#prodName').val();
        var prodBrand = $('#prodBrand').val();
        var prodAddress = $('#prodAddress').val();
        var prodAmount = $('#prodAmount').val();
        var prodKrw = $('#prodKrw').val();
        var prodKrw = prodKrw.replace(",", "");
        var totalKrw = prodKrw * prodAmount;
        var userName = $('userName').val();
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : prodName,
            amount : totalKrw,
            buyer_email : prodAddress,
            buyer_name : userName,
            buyer_tel : '010-2538-2462',
            buyer_addr : prodAddress,
            buyer_postcode : '123-456',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
        	console.log("rsp상태:"+rsp.success);
        	
        	var serial_imp_uid =
        	{
                imp_uid: rsp.imp_uid
            };
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기    
            	$.ajax({	
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    headers: { "Content-Type": "application/json" },
                    data: JSON.stringify(serial_imp_uid)
                }).done(function(info) {
                	console.log("결제 정보:"+ JSON.stringify(info));
         	   //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우     
                    msg = '결제가 완료되었습니다.';
                    msg += '\n고유ID : ' + info.imp_uid;
                    msg += '\n상점 거래ID : ' + info.merchant_uid;
                    msg += '\결제 금액 : ' + info.amount;
                    msg += '카드 승인번호 : ' + info.apply_num;
                    
                    alert(msg);

                    //성공시 이동할 페이지
        /*            location.href="/auth/product";*/
                }).fail(function(xhr, status, errorThrown) {
                   console.log("오류명: " + errorThrown + "<br>"+"상태: " + status);
                });
                } else {
                	 msg = '결제에 실패하였습니다.';
                     msg += '에러내용 : ' + data.error_msg;
                     //실패시 이동할 페이지
                     location.href="/auth/product";
                     alert(msg);
                }
            })
        };