package com.hwsin.blog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwsin.blog.model.Visit;
import com.hwsin.blog.service.VisitService;

@RestController
public class VisitApiController {

	@Autowired
	private VisitService visitService;

	@GetMapping("/auth/VisitInfo")
	public List<Visit> getVisitCount() throws Exception {
		
		return visitService.getVisitTimeList();
	}
}
