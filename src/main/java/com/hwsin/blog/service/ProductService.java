package com.hwsin.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwsin.blog.dto.ReplySaveRequestDto;
import com.hwsin.blog.model.Product;
import com.hwsin.blog.model.RoleType;
import com.hwsin.blog.model.Users;
import com.hwsin.blog.repository.ProductRepository;
import com.hwsin.blog.repository.UsersRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public void 상품등록하기(Product product, Users user) {
		product.setCount(0);
		product.setUser(user);
		productRepository.save(product);
	}

	public Page<Product> 상품목록보기(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Product 상품상세보기(int id) {
		return productRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("상품 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 상품삭제하기(int id) {
		System.out.println("글삭제하기 : " + id);
		productRepository.deleteById(id);
	}

	@Transactional
	public void 상품수정하기(int id, Product requestProduct) {
		Product product = productRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("상품 찾기 실패 : 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		product.setProdName(requestProduct.getProdName());
		product.setProdBrand(requestProduct.getProdBrand());
		product.setContent(requestProduct.getContent());
		product.setProdKrw(requestProduct.getProdKrw());
		product.setFilePath(requestProduct.getFilePath());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
	}
	

}
