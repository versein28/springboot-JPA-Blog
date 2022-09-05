package com.hwsin.blog.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hwsin.blog.dto.ProductDto.WishItemResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class Users {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 100, unique = true) // unique 처리로 인해 ID중복 가입 방지되네요
	private String username; // 아이디

	@Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화)
	private String password;

	@Column(nullable = false, length = 50)
	private String email; // myEmail, my_email

	// @ColumnDefault("user")
	// DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER, SELLER

	private String oauth; // kakao, google

	@Column(length = 50)
	private String bno; // 사업자등록번호

	@Column(length = 50)
	private String bt; // 사업자등록타입

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cartId")
	private Cart cart;

	// 내가 직접 시간을 넣으려면 Timestamp.valueOf(LocalDateTime.now())
	@CreationTimestamp
	private Timestamp createDate;

	public void createCart(Cart cart) {
		this.cart = cart;
	}

	public void addCartItem(CartProduct cartItem) {
		cart.addCartProducts(cartItem);
	}

	public boolean checkCartItemDuplicate(CartProduct cartItem) {
		return cart.getWishList().stream().map(CartProduct::getProduct)
				.anyMatch(v -> v.getId() == cartItem.getProductId());
	}

	/*
	 * public List<WishItemResponse> getWishList() {
	 * 
	 * return cart.getWishList().stream().map(CartProduct::toWishItemDto)
	 * .collect(Collectors.toList()); }
	 */
}
