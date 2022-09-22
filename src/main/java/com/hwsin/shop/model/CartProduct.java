package com.hwsin.shop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hwsin.shop.dto.ProductDto.WishItemResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartId")
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prodId")
	private Product product;

	@Column
	private int count;
	
	@Builder
	public CartProduct(Cart cart, Product product) {
		this.cart = cart;
		this.product = product;
	}
	
    public int getProductId() {
        return product.getId();
    }
    
    public WishItemResponse toWishItemDto() {
        return WishItemResponse.builder()
            .id(this.id)
            .prodId(product.getId())
            .prodBrand(product.getProdBrand())
            .prodName(product.getProdName())
            .prodKrw(product.getProdKrw())
            .filePath(product.getFilePath())
            .count(this.getCount())
            .build();
    }
}
