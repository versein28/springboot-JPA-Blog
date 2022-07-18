package com.hwsin.blog.dto;

import com.google.gson.JsonObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class IamportTokenDto {

	private String code;
	private String message;
	private JsonObject response = new JsonObject();
}
