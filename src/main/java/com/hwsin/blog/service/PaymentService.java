package com.hwsin.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwsin.blog.model.Board;
import com.hwsin.blog.model.Iamport;
import com.hwsin.blog.model.Users;
import com.hwsin.blog.repository.IamportRepository;

@Service
public class IamportService {
	
	@Autowired IamportRepository iamportRepository;
	
	@Transactional
	public void 저장하기(Iamport iamport) { 
		iamportRepository.save(iamport);
	}
}
