package com.hwsin.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hwsin.shop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
