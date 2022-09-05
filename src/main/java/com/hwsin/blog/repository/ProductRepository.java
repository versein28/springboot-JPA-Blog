package com.hwsin.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.blog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
		List<Product> findTop6ByContentContaining(String content);
		Page<Product> findByContentContaining(Pageable pageable, String keyword);
}