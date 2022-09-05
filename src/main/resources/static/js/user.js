let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.save();
			});
			$("#btn-update").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.update();
			});
			$("#btn-search").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.pwdSearch();
			});
		},

		save: function(){
			// 닉네임 중복 검사
			$('#user-check').click( function(){
				$.ajax({ 
					type: "GET",
					url: "/auth/joinProc",
					data: JSON.stringify(data), // http body데이터
					contentType: "application/json; charset=utf-8",// body데이터가 어떤
																	// 타입인지(MIME)
					dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
										// json이라면) => javascript오브젝트로 변경
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
			});
			
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
	        
			// alert('user의 save함수 호출됨');
				let data = {
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val(),
					role: $("#role").val(), // USER or SELLER
					// SELLER는 사업자 관련 정보 추가
					bno: $("#b_no").val(), // 사업자 등록 번호
					bt: $("#bt").val() // 사업자 등록 타입
				};

			// console.log(data);
			
			// ajax호출시 default가 비동기 호출
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
			$.ajax({ 
				type: "POST",
				url: "/auth/joinProc",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
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
				url: "/user",
				data: JSON.stringify(data), // http body데이터
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
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
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