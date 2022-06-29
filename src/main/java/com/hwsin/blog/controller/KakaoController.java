package com.hwsin.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class KakaoController {

	@GetMapping("/order/paySuccess") // 결제 성공
	public String paySuccess() {
		
		return "product/product";
	}
	
	@GetMapping("/order/payFail") // 결제 실패
	public String payFail() {
		
		return "product/product";
	}
}
