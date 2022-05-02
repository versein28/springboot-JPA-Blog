package com.hwsin.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.blog.config.auth.PrincipalDetail;
import com.hwsin.blog.dto.ResponseDto;
import com.hwsin.blog.model.RoleType;
import com.hwsin.blog.model.Users;
import com.hwsin.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	// @Autowired
	// private HttpSession session;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Users user) { // username,email,password
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// 스프링 시큐리티 이용해서 로그인!!(아래는 전통적인 방식)
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody Users user, HttpSession session) {
	 * System.out.println("UserApiController : login 호출됨"); Users principal =
	 * userService.로그인(user); // principal = 접근주체
	 * 
	 * if(principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody Users user) {
		System.out.println("11111111");
		userService.회원수정(user);
		//여기선ㄴ 트랜잭션이 종료되기 때문에 DB에 값은 변경이 돘음.
		//그러나 세션값은 변경 되지 않은 상태이기 때문에 직접 세션값을 변경해줄 것임
		//세션 등록
		System.out.println("2222222222");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}
