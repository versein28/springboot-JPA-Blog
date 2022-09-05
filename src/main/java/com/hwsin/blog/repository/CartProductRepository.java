package com.hwsin.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.blog.dto.ProductDto.WishItemResponse;
import com.hwsin.blog.model.Cart;
import com.hwsin.blog.model.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
}
