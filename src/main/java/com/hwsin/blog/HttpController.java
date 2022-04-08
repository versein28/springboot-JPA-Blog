package com.hwsin.blog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {
	
	@GetMapping("/get")
	public String getTest() {
		
		return "get 요청";
	}

	@PostMapping
	public String postTest() {
		
		return "post 요청";
	}
	
	@PutMapping
	public String putTest() {
		
		return "put 요청";
	}
	
	@DeleteMapping
	public String deleteTest() {
		
		return "delete 요청";
	}
}
