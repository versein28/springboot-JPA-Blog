package com.hwsin.blog.controller.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.blog.service.PaymentService;

@RestController
public class KakaoApiController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/payments/complete") // DB에 담는 역할 -> 추후 예정
	public String paymentComplete(@RequestBody String imp_uid) throws ParseException {
		String token = paymentService.GetToken();

		JSONObject objData = (JSONObject) new JSONParser().parse(token);
		JSONObject response = (JSONObject) objData.get("response");
		String access_token = (String) response.get("access_token");

		return access_token;

	}

}
