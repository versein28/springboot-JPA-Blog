package com.hwsin.shop.controller.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.hwsin.shop.dto.ResponseDto;
import com.hwsin.shop.model.Board;
import com.hwsin.shop.model.Product;
import com.hwsin.shop.service.CartService;
import com.hwsin.shop.config.auth.PrincipalDetail;

@RestController
public class MyPageApiController {

	@Autowired
	private CartService cartService;

	@PostMapping("/api/cart")
	ResponseDto<Integer> addWishList(@RequestBody Product product, @AuthenticationPrincipal PrincipalDetail principal) {
		cartService.장바구니추가(product, principal.getUser());

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/cart/{id}")
	ResponseDto<Integer> updateCount(@RequestBody JSONObject reqeust, @PathVariable int id) {
		int count = Integer.parseInt((String) reqeust.get("count"));
		cartService.장바구니수량변경(count, id);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/cart/{id}")
	ResponseDto<Integer> deleteWishList(@PathVariable int id) {
		cartService.장바구니삭제(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping("/api/cart/delete") // 장바구니 선택 삭제
	ResponseDto<Integer> deleteSelectedWishList(@RequestParam(value = "checkBoxArr[]") List<String> checkBoxArr) {
		int size = checkBoxArr.size();
		//List to Array
		String arr[] = checkBoxArr.toArray(new String[size]);
		//String Array to Int Array
		 int[] values = Arrays.stream(arr)
                 .mapToInt(Integer::parseInt)
                 .toArray();
		 
		for (int i = 0; i < size; i++) {
			cartService.장바구니삭제(values[i]);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
