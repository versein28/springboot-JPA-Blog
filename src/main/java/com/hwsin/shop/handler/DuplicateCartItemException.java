package com.hwsin.shop.handler;

public class DuplicateCartItemException extends RuntimeException {

	public DuplicateCartItemException(String message) {
		super(message);
	}
}