package com.hwsin.shop.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hwsin.shop.dto.IamportDto;
import com.hwsin.shop.model.Board;
import com.hwsin.shop.model.Users;


@Service
public class IamportService { // 아임포트 REST API

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
		JSONObject body = new JSONObject(map);
		HttpEntity<JSONObject> entity = new HttpEntity<>(body, headers);

		return restTemplate.postForObject("https://api.iamport.kr/users/getToken", entity, String.class);
	}

	public JSONObject GetPayment(String token, String imp_uid) throws ParseException {
		JSONObject objData = (JSONObject) new JSONParser().parse(token);
		JSONObject response = (JSONObject) objData.get("response");
		String access_token = (String) response.get("access_token");

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", access_token);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		IamportDto iamportDto = restTemplate.postForObject("https://api.iamport.kr/payments/" + imp_uid, entity,
				IamportDto.class);
		JSONObject rsp = iamportDto.getResponse();

		return rsp;

	}
}
