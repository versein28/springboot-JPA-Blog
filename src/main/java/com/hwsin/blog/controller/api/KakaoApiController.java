package com.hwsin.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.blog.config.auth.PrincipalDetail;
import com.hwsin.blog.dto.ResponseDto;
import com.hwsin.blog.model.Board;

@RestController
public class KakaoApiController {

	@GetMapping("/payments/complete") // DB에 담는 역할 -> 추후 예정
	public void kakaopay(String imp_uid) {
		System.out.println("결제번호:"+imp_uid);
		System.out.println("결제 완료");
	}
	
	
}
