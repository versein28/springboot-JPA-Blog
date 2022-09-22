//csrf토큰
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
// 회원가입 폼 
function UserJoin() {
	console.log("User!");
	location.href = "/auth/userJoinForm";
}
function SellerJoin() {
	console.log("Seller!");
	location.href = "/auth/sellerJoinForm";
}
// 닉네임 중복 검사
$('#user-check').click(function() {
    // 유효성 검사
    if ($('#username').val() == "") {
        alert("아이디를 입력해 주십시오.");
        return false;
    }
    // 닉네임
    var data = {
        username: $("#username").val()
    }
    $.ajax({
        type: "POST",
        url: "/auth/userCheck",
        data: JSON.stringify(data), // http body데이터
		beforeSend : function(xhr)
        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
        },
        contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
        dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
    }).done(function(resp) {

        if (resp == null) { // 가입가능
            alert("가입 가능한 닉네임입니다.");
            if ($('#role').val() == "USER") { // 일반회원
                $("#btn-save").removeAttr('disabled'); //회원가입 버튼 활성화
            }
        } else {
            alert("중복된 닉네임입니다.");
            $("#btn-save").attr("disabled", true);
        }
    }).fail(function(error) {
        alert(JSON.stringify(error)); //에러
    });
});

//사업자등록증 검사
$('#bl-check').click(function() {
    var b_no = $("#b_no").val(); //사업자등록번호

    var data = {
        "b_no": [b_no]
    };
    console.log("data" + JSON.stringify(data));
    $.ajax({
        type: "POST",
        url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=5hBVGxDy%2BBEx6gbo3OtIKOdSDfS1%2BqXy7gPpAHZuqTwsg1US3qCqT3VDg06YKUpUoVQ0Lio5jqvgjJ%2Bo8Vzc%2BA%3D%3D",
        data: JSON.stringify(data),
        contentType: "application/json",
        accept: "application/json",
        dataType: "JSON",
        success: function(result) {
            if (result.data[0].tax_type == "국세청에 등록되지 않은 사업자등록번호입니다.") {
            	alert("실패: "+result.data[0].tax_type); // 인증실패
            	$("#btn-save").attr("disabled", true); //회원가입 버튼 비활성화
            }
            else {
            	alert("성공: "+result.data[0].tax_type); // 인증완료
            	$("#btn-save").removeAttr('disabled'); //회원가입 버튼 활성화
                $("#bt").val(result.data[0].tax_type); //사업자등록타입
            }
        },
        error: function(result) {
            console.log(result.responseText); // responseText의 에러메세지 확인
        }
    });
});

let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ // function(){} , ()=>{} this를 바인딩하기 위해서!!
				this.save();
			});
			$("#btn-update").on("click", ()=>{ // function(){} , ()=>{} this를 바인딩하기 위해서!!
				this.update();
			});
			$("#btn-remove").on("click", ()=>{ // function(){} , ()=>{} this를 바인딩하기 위해서!!
				this.remove();
			});
			$("#btn-search").on("click", ()=>{ // function(){} , ()=>{} this를 바인딩하기 위해서!!
				this.pwdSearch();
			});
		},

		save: function(){	
			// ajax호출시 default가 비동기 호출
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
			
			// 유효성 검사
			if ($('#username').val() == "") {
	            alert("아이디를 입력해 주십시오.");
	            return false;
	        }    
	        if ($('#password').val() == "") {
	            alert("비밀번호를 입력해 주십시오.");
	            return false;
	        }
	        // 이메일 검증 스크립트 작성
	        var email = $("#email").val();

	        var regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
	        
	        if(email == "") {
	        	alert("이메일을 작성해주세요.");
		        return false;
	        }
	        else if(!regExp.test(email)){
	          alert("이메일 형식이 아닙니다.");
	          return false;
	        }
	        
			let data = {
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val(),
					role: $("#role").val(), // USER or SELLER
					// SELLER는 사업자 관련 정보 추가
					bno: $("#b_no").val(), // 사업자 등록 번호
					bt: $("#bt").val() // 사업자 등록 타입
				};
			
			$.ajax({ 
				type: "POST",
				url: "/auth/joinProc",
				data: JSON.stringify(data), // http body데이터
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        },
				contentType: "application/json; charset=utf-8",// body데이터가 어떤 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				if(resp.status === 200){
					alert("회원가입이 완료되었습니다.");
					location.href = "/";
				}else{
					alert("회원가입에 실패하였습니다.");
				}

			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
		},
		
		update: function(){
			// alert('user의 save함수 호출됨');
			let data = {
					id: $("#id").val(),
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val()
			};
			
			$.ajax({ 
				type: "PUT",
				url: "/api/user",
				data: JSON.stringify(data), // http body데이터
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        },
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				alert("회원수정이 완료되었습니다.");
				// console.log(resp);
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
		},
		
		remove: function(){
			let data = {
					id: $("#id").val(),
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: "/api/deleteUser",
				data: JSON.stringify(data), // http body데이터
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        },
				contentType: "application/json; charset=utf-8",// body데이터가 어떤타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				if(resp.status == 200 && resp.data == 1)
					alert("회원탈퇴가 완료되었습니다.");
				else {
					console.log(JSON.stringify(resp));
				}
				//location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
		},
		
		pwdSearch: function(){
			// alert('user의 save함수 호출됨');
			let data = {
					username: $("#username").val(),
					email: $("#email").val()
			};
			
			$.ajax({ 
				type: "POST",
				url: "/auth/pwdSearch",
				data: JSON.stringify(data), // http body데이터
				beforeSend : function(xhr)
		        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
					xhr.setRequestHeader(header, token);
		        },
				contentType: "application/json; charset=utf-8",// body데이터가 어떤 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				if(resp.status === 500){
					alert("비밀번호 찾기가 실패하였습니다.");
				}else{
					alert("비밀번호 찾기가 완료되었습니다.");
					location.href = "/";
				}

			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
		},
}

index.init();