package com.hwsin.blog.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwsin.blog.dto.IamportDto;
import com.hwsin.blog.model.Iamport;
import com.hwsin.blog.service.IamportService;
import com.hwsin.blog.service.PaymentService;

@RestController
public class KakaoApiController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private IamportService iamportService;
	
	@PostMapping("/payments/complete") // DB에 담는 역할 
	public JSONObject paymentComplete(@RequestBody JSONObject imp_uid) throws IOException, ParseException {
		String token = paymentService.GetToken(); // 토큰 받기
		String StrImpUid = (String) imp_uid.get("imp_uid");
		JSONObject rsp = paymentService.GetPayInfo(token, StrImpUid); // 결제정보 받기
		
		Map<String,Object> map = new HashMap<String, Object>();

		String impUid = (String) rsp.get("imp_uid");
		String merchantUid = (String) rsp.get("merchant_uid");
		String buyerEmail = (String) rsp.get("buyer_email");
		String buyerPostcode = (String) rsp.get("buyer_postcode");
		String name = (String) rsp.get("name");
		int amount = (int) rsp.get("amount");
		
		map.put("imp_uid", impUid);
		map.put("merchant_uid", merchantUid);
		map.put("buyer_email", buyerEmail);
		map.put("buyer_postcode", buyerPostcode);
		map.put("name", name);
		map.put("amount", amount);
		
		ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		Iamport iamport = mapper.convertValue(map, Iamport.class);
		
		iamportService.저장하기(iamport);
		
		return rsp;
	}

}
