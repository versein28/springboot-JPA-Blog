package com.hwsin.blog.dto;


import org.json.simple.JSONObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class IamportDto {

	private String code;
	private String message;
	private JSONObject response = new JSONObject();
}
