package com.hwsin.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwsin.shop.dto.CartProductDto;
import com.hwsin.shop.dto.ProductDto.WishItemResponse;
import com.hwsin.shop.handler.DuplicateCartItemException;
import com.hwsin.shop.model.Board;
import com.hwsin.shop.model.Cart;
import com.hwsin.shop.model.CartProduct;
import com.hwsin.shop.model.Product;
import com.hwsin.shop.model.Users;
import com.hwsin.shop.repository.CartProductRepository;
import com.hwsin.shop.repository.CartRepository;
import com.hwsin.shop.repository.ProductRepository;
import com.hwsin.shop.repository.UsersRepository;
import com.hwsin.shop.config.auth.PrincipalDetail;
import com.hwsin.shop.config.auth.PrincipalDetailService;

import lombok.RequiredArgsConstructor;

@Service
public class CartService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private CartProductRepository cartProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Transactional(readOnly = true)
	public Page<WishItemResponse> 장바구니조회(Users users, int pageNo) {
		users = usersRepository.findById(users.getId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

		Cart cart = cartRepository.findById(users.getCart().getId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니 입니다."));

		List<WishItemResponse> allitems = cart.getWishList().stream().map(CartProduct::toWishItemDto)
				.collect(Collectors.toList());
		// pageable 객체 수동생성
		Pageable pageable = PageRequest.of(pageNo, 4, Sort.by(Sort.Direction.DESC, "id"));
		//List -> Page객체로 변환
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), allitems.size());
		final Page<WishItemResponse> carts = new PageImpl<>(allitems.subList(start, end), pageable, allitems.size());

		return carts;
	}

	@Transactional
	public void 장바구니삭제(int id) {
		cartProductRepository.deleteById(id);
	}

	@Transactional
	public void 장바구니수량변경(int count, int id) {
		CartProduct cartProduct = cartProductRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니 상품입니다."));

		cartProduct.setCount(count);
	}

	@Transactional
	public void 장바구니추가(Product product, Users users) { //

		users = usersRepository.findById(users.getId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

		product = productRepository.findById(product.getId())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 입니다."));

		CartProduct cartProduct = cartProductRepository.save(new CartProduct(users.getCart(), product));
		cartProduct.setCount(1);// default 수량 설정

		if (users.checkCartItemDuplicate(cartProduct)) {
			throw new DuplicateCartItemException("이미 장바구니에 담긴 상품 입니다.");
		}

		users.addCartItem(cartProduct);
	}

}
