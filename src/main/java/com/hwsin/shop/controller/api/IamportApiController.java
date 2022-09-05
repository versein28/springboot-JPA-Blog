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
	private PaymentService PaymentService;
	
	@PostMapping("/payment/complete") // 
	public JSONObject getCertification(@RequestBody JSONObject data, @AuthenticationPrincipal PrincipalDetail principal) throws IOException, ParseException {
		// 토큰 받기
		String token = iamportService.GetToken(); 
		String StrImpUid = (String) data.get("imp_uid");

		// 결제정보 받기
		JSONObject rsp = iamportService.GetPayment(token, StrImpUid); 
		
		Map<String,Object> map = new HashMap<String, Object>();

		String impUid = (String) rsp.get("imp_uid");
		String merchantUid = (String) rsp.get("merchant_uid");
		String buyerAddr = (String) rsp.get("buyer_addr");
		String buyerTel = (String) data.get("buyer_tel");
		int prodId = Integer.parseInt(String.valueOf(data.get("prodId")));
		int amount = Integer.parseInt(String.valueOf(rsp.get("amount")));
		int quantity = Integer.parseInt(String.valueOf(data.get("quantity")));
		
		map.put("imp_uid", impUid);
		map.put("merchant_uid", merchantUid);
		map.put("buyer_addr", buyerAddr);
		map.put("buyer_tel", buyerTel);
		map.put("amount", amount);
		map.put("qty", quantity);
		
		ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		Payment Payment = mapper.convertValue(map, Payment.class);
		
		PaymentService.결제하기(Payment, prodId ,principal.getUser());
		
		return data;
	}

}
