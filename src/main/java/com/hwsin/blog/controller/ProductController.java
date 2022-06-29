package com.hwsin.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hwsin.blog.config.auth.PrincipalDetail;
import com.hwsin.blog.dto.ResponseDto;
import com.hwsin.blog.model.Board;
import com.hwsin.blog.model.Product;
import com.hwsin.blog.service.BoardService;
import com.hwsin.blog.service.ProductService;
import com.hwsin.blog.service.S3Service;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/auth/product") // 상품 목록보기
	public String product(Model model,
			@PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("products", productService.상품목록보기(pageable));

		return "product/product";
	}

	@GetMapping("/product/{id}") // 상품 디테일 페이지 보기
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.상품상세보기(id));

		return "product/detail";
	}
	
	@GetMapping("/product/{id}/updateForm") // 상품 수정 페이지 보기
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.상품상세보기(id));

		return "product/updateForm";
	}
	
	// USERS 권한이 필요
	@GetMapping("/product/saveForm")
	public String saveForm() {
		return "product/saveForm";
	}

}
