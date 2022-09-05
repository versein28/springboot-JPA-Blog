package com.hwsin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import com.hwsin.shop.model.Board;
import com.hwsin.shop.model.Payment;
import com.hwsin.shop.model.Product;
import com.hwsin.shop.model.Users;
import com.hwsin.shop.repository.PaymentRepository;
import com.hwsin.shop.repository.ProductRepository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hwsin.shop.dto.IamportDto;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ProductRepository productRepository;

	@Transactional
	public void 결제하기(Payment payment, int prodId, Users user) {
		payment.setUser(user);
		paymentRepository.save(payment); // 결제내역 저장

		Product product = productRepository.findById(prodId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 입니다."));

		payment.setProduct(product); // 연관관계의 주인

		product.subQty(payment); // Product 재고량 감소
		product.addCount(payment); // Product 판매량 증가

	}

	@Transactional(readOnly = true)
	public Page<Payment> 결제내역조회(Users users, Pageable pageable) {
		return paymentRepository.findByUserId(users.getId(), pageable);
	}

	@Transactional(readOnly = true)
	public Page<Payment> 선택날짜결제내역조회(Users users, Pageable pageable, String fromDate, String toDate) {
		return paymentRepository.findBySelectedDate(users.getId(), fromDate, toDate, pageable);

	}
}
