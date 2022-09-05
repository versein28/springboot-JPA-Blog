package com.hwsin.blog.controller.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hwsin.blog.config.auth.PrincipalDetail;
import com.hwsin.blog.dto.ResponseDto;
import com.hwsin.blog.model.Product;
import com.hwsin.blog.service.ProductService;
import com.hwsin.blog.service.S3Service;

@RestController
public class ProductApiController {

	@Autowired
	ProductService productService;

	@Autowired
	S3Service s3Service;

	@PostMapping("/api/product") // 상품 등록하기
	public ResponseDto<Integer> save(Product product, @RequestParam("file") MultipartFile file,
			@AuthenticationPrincipal PrincipalDetail principal) throws IOException {

		if (!file.isEmpty()) {
			String imgPath = s3Service.upload(file, "product");
			product.setFilePath(imgPath);
		}

		productService.상품등록하기(product, principal.getUser());

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping("/api/product/{id}") // 상품 수정하기
	public ResponseDto<Integer> update(@PathVariable int id, Product product, MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String imgPath = s3Service.upload(file, "product");
			product.setFilePath(imgPath);
		}

		productService.상품수정하기(id, product);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/product/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id) {
		productService.상품삭제하기(id);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/product/rank") // 실시간 랭킹
	public List<Product> getRank(@RequestBody Product product)  {
		List<Product> result = productService.실시간랭킹보기(product);

		return result;
	}
}
