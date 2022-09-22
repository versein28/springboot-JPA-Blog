package com.hwsin.shop.controller.api;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwsin.shop.dto.IamportDto;
import com.hwsin.shop.model.Payment;
import com.hwsin.shop.service.PaymentService;
import com.hwsin.shop.config.auth.PrincipalDetail;
import com.hwsin.shop.service.IamportService;

@RestController
public class IamportApiController {

	@Autowired
	private IamportService iamportService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payments/complete") // 결제하기 
	public JSONObject getCertification(@RequestBody JSONObject data, @AuthenticationPrincipal PrincipalDetail principal) throws IOException, ParseException {
		// 엑세스 토큰 받기
		String token = iamportService.getToken(); 
		String imp_uid = (String) data.get("imp_uid");

		// import에서 결제정보 받기
		JSONObject rsp = iamportService.getPayment(token, imp_uid); 
		
		Map<String,Object> map = new HashMap<String, Object>();

		String impUid = (String) rsp.get("imp_uid");
		String merchantUid = (String) rsp.get("merchant_uid");
		String buyerAddr = (String) rsp.get("buyer_addr");
		String buyerTel = (String) data.get("buyer_tel");
		int prodId = Integer.parseInt(String.valueOf(data.get("prodId")));
		int amount = Integer.parseInt(String.valueOf(rsp.get("amount")));
		int quantity = Integer.parseInt(String.valueOf(data.get("quantity")));
		
		map.put("impUid", impUid);
		map.put("merchantUid", merchantUid);
		map.put("buyerAddr", buyerAddr);
		map.put("buyerTel", buyerTel);
		map.put("amount", amount);
		map.put("qty", quantity);
		
		ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		Payment Payment = mapper.convertValue(map, Payment.class);
		
		paymentService.결제하기(Payment, prodId ,principal.getUser());
		
		return data;
	}
	
	@PostMapping("/payments/cancel")
	public JSONObject getRefund(@RequestBody JSONObject data, @AuthenticationPrincipal PrincipalDetail principal) throws ParseException {				
		String merchant_uid = (String) data.get("merchant_uid");
		int cancel_request_amount = Integer.parseInt(String.valueOf(data.get("cancel_request_amount")));
		String reason = (String) data.get("reason");	
		// 엑세스토큰 받기
		String token = iamportService.getToken(); 
		// 거래내역 조회
		String imp_uid = paymentService.거래내역조회(merchant_uid);
		
		Map<String,Object> map = new HashMap<String, Object>();	
		map.put("reason", reason);
		map.put("imp_uid", imp_uid);
		map.put("amount", cancel_request_amount);
		
		// import에 환불요청하기
		JSONObject rsp = iamportService.getRefund(map,token); 
		// 환불요청 성공시 결제내역 삭제
		if(Integer.parseInt(String.valueOf(rsp.get("code"))) == 0) {
			paymentService.주문취소(merchant_uid);
		}
		return rsp;
	}
}
