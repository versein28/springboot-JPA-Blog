package com.hwsin.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.shop.dto.ProductDto.WishItemResponse;
import com.hwsin.shop.model.Cart;
import com.hwsin.shop.model.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
}
