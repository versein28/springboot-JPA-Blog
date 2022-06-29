package com.hwsin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwsin.blog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}