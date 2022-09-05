package com.hwsin.blog.dto;

import org.json.simple.JSONObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
	private int id;
	private int prodId;
	private int cartId;
	private int count;

}
