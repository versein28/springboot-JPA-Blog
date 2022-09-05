package com.hwsin.blog.handler;

public class DuplicateCartItemException extends RuntimeException {

	public DuplicateCartItemException(String message) {
		super(message);
	}
}