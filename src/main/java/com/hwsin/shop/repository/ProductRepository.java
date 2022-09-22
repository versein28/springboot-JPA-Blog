package com.hwsin.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hwsin.shop.model.Product;
import com.hwsin.shop.model.Reply;

public interface ProductRepository extends JpaRepository<Product, Integer> {
		Page<Product> findTop6ByProdNameContaining(@Param("content") String content, Pageable pageable); // 연속 사용시 파라미터 명확한 바인딩!!
		Page<Product> findByContentContaining(Pageable pageable, String keyword);
			
		boolean existsByUserId(int userId);
		List<Product> findByUserId(int userId);
}