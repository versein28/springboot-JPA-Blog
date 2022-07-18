package com.hwsin.blog.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
	@Value("${imp_key}")
	private String imp_key;

	@Value("${imp_secret}")
	private String imp_secret;

	public String GetToken() {
		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> map = new HashMap<>();
		map.put("imp_key", imp_key);
		map.put("imp_secret", imp_secret);

		// 서버로 요청할 Body
		JSONObject body =  new JSONObject(map);
		HttpEntity<JSONObject> entity = new HttpEntity<>(body, headers);

		return restTemplate.postForObject("https://api.iamport.kr/users/getToken", entity, String.class);
	}
}
