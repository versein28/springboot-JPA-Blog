package com.hwsin.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hwsin.shop.dto.ProductDto.WishItemResponse;
import com.hwsin.shop.model.Payment;
import com.hwsin.shop.model.Users;
import com.hwsin.shop.service.CartService;
import com.hwsin.shop.service.PaymentService;
import com.hwsin.shop.service.UserService;
import com.hwsin.shop.config.auth.PrincipalDetail;

@Controller
public class MyPageController {

	@Autowired
	CartService cartService;

	@Autowired
	PaymentService paymentService;

	@GetMapping("/user/myPage") // 결제내역 조회
	public String getMyPayment(Model model, @AuthenticationPrincipal PrincipalDetail principal,
			@PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value = "fromDate", required = false, defaultValue = "") String fromDate,
			@RequestParam(value = "toDate", required = false, defaultValue = "") String toDate) {
		if (!fromDate.isEmpty() && !toDate.isEmpty())
			model.addAttribute("payments", paymentService.선택날짜결제리스트조회(principal.getUser(), pageable, fromDate, toDate));
		else
			model.addAttribute("payments", paymentService.결제리스트조회(principal.getUser(), pageable));
		model.addAttribute("user", principal.getUser()); // 사용자 정보
		
		return "user/myPage";
	}

	@GetMapping("/user/myCart") // 장바구니 조회
	public String getMyPage(Model model, @AuthenticationPrincipal PrincipalDetail principal,
			@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo) {
		model.addAttribute("carts", cartService.장바구니조회(principal.getUser(), pageNo));
		model.addAttribute("user", principal.getUser()); // 사용자 정보

		return "user/myCart";
	}

	/*
	 * 장바구니 조회 페이지네이션 테스트
	 * 
	 * @GetMapping("/user/test")
	 * 
	 * @ResponseBody public Page<WishItemResponse> get1(Model
	 * model, @AuthenticationPrincipal PrincipalDetail principal,
	 * 
	 * @RequestParam(required = false, defaultValue = "0", value = "page") int
	 * pageNo, Pageable pageable) {
	 * 
	 * return cartService.장바구니조회(principal.getUser(), pageable, pageNo); }
	 */
}
