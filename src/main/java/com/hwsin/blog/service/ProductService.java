package com.hwsin.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwsin.blog.dto.ReplySaveRequestDto;
import com.hwsin.blog.model.Payment;
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

	/* search */
	@Transactional(readOnly = true)
	public Page<Product> 검색목록보기(Pageable pageable, String keyword) {
		return productRepository.findByContentContaining(pageable, keyword);
	}
	
	/* sort */
	@Transactional(readOnly = true)
	public Page<Product> 내림차순목록보기(Pageable pageable, int pageNo, String category_name) {
		/* 넘겨받은 category_name 를 이용해 내림차순하여 Pageable 객체 반환 */
		pageable = PageRequest.of(pageNo, 4, Sort.by(Sort.Direction.DESC, category_name));

		return productRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Page<Product> 오름차순목록보기(Pageable pageable, int pageNo, String category_name) {
		/* 넘겨받은 category_name 를 이용해 오름차순하여 Pageable 객체 반환 */
		pageable = PageRequest.of(pageNo, 4, Sort.by(Sort.Direction.ASC, category_name));

		return productRepository.findAll(pageable);
	}
	
	/* search && sort */
	public Page<Product> 검색내림차순목록보기(Pageable pageable, int pageNo, String keyword, String category_name) {
		pageable = PageRequest.of(pageNo, 4, Sort.by(Sort.Direction.DESC, category_name));
		return productRepository.findByContentContaining(pageable, keyword);
	}
	
	public Page<Product> 검색오름차순목록보기(Pageable pageable, int pageNo, String keyword, String category_name) {
		pageable = PageRequest.of(pageNo, 4, Sort.by(Sort.Direction.ASC, category_name));
		return productRepository.findByContentContaining(pageable, keyword);
	}
	
	/* 실시간 랭킹  */
	public List<Product> 실시간랭킹보기(Product product) {
		return productRepository.findTop6ByContentContaining(product.getContent());
	}
}
