package com.hwsin.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hwsin.shop.model.RoleType;
import com.hwsin.shop.model.Visit;
import com.hwsin.shop.service.BoardService;
import com.hwsin.shop.service.ProductService;
import com.hwsin.shop.service.VisitService;
import com.hwsin.shop.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ProductService productService;
	
	// 컨트롤러에서 세션을 어떻게 찾는지?
	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping("/")
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));
		
		return "index";
	}

	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		boardService.조회수증가(id);
		model.addAttribute("board", boardService.글상세보기(id));

		return "board/detail";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}

	// ADMIN 권한이 필요
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
