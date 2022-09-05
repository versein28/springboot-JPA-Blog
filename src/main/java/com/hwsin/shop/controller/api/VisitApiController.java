package com.hwsin.shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.shop.model.Visit;
import com.hwsin.shop.service.VisitService;

@RestController
public class VisitApiController {

	@Autowired
	private VisitService visitService;

	@GetMapping("/auth/VisitInfo")
	public List<Visit> getVisitCount() throws Exception {
		
		return visitService.getVisitTimeList();
	}
}
