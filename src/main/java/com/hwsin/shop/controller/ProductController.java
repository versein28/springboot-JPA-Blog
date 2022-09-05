package com.hwsin.shop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.hwsin.shop.dto.ResponseDto;
import com.hwsin.shop.model.Board;
import com.hwsin.shop.model.Product;
import com.hwsin.shop.service.BoardService;
import com.hwsin.shop.service.ProductService;
import com.hwsin.shop.service.S3Service;
import com.hwsin.shop.config.auth.PrincipalDetail;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/auth/product") // 정렬 목록보기
	public String getProduct(@RequestParam(required = false, defaultValue = "") String keyword,
			@RequestParam(required = false, defaultValue = "") String category_name,
			@RequestParam(required = false, defaultValue = "") String reverseOrder,
			@RequestParam(required = false, defaultValue = "최신상품순(default)") String content_name,
			@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
			@PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("content_name", content_name); // 최신상품순(default)
		if (keyword.isEmpty() && reverseOrder.equals("true") && !category_name.isEmpty())
			model.addAttribute("products", productService.오름차순목록보기(pageable, pageNo, category_name));
		else if (keyword.isEmpty() && reverseOrder.isEmpty() && !category_name.isEmpty())
			model.addAttribute("products", productService.내림차순목록보기(pageable, pageNo, category_name));
		else if (!keyword.isEmpty() && reverseOrder.equals("true") && !category_name.isEmpty())
			model.addAttribute("products", productService.검색오름차순목록보기(pageable, pageNo, keyword, category_name));
		else if (!keyword.isEmpty() && reverseOrder.isEmpty() && !category_name.isEmpty())
			model.addAttribute("products", productService.검색내림차순목록보기(pageable, pageNo, keyword, category_name));
		else if (!keyword.isEmpty() && reverseOrder.isEmpty() && category_name.isEmpty())
			model.addAttribute("products", productService.검색목록보기(pageable, keyword));
		else
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

	// ADMIN or SELLER 권한이 필요
	@PreAuthorize("hasAnyRole('ADMIN','SELLER')")
	@GetMapping("/product/saveForm")
	public String saveForm() {
		return "product/saveForm";
	}

}
