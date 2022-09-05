package com.hwsin.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwsin.shop.model.CartProduct;
import com.hwsin.shop.model.Users;
import com.hwsin.shop.model.Visit;
import com.hwsin.shop.repository.VisitRepository;

@Service
public class VisitService {

	@Autowired
	VisitRepository visitRepository;

	@Transactional
	public int setVisitTotalCount(String ip) {
		return visitRepository.setVisitTotalCount(ip);
	}

	/*
	 * @Transactional public int getVisitTotalCount() { return
	 * visitRepository.getVisitTotalCount(); }
	 * 
	 * @Transactional public int getVisitTodayCount() { return
	 * visitRepository.getVisitTodayCount(); }
	 */

	@Transactional
	public List<Visit> getVisitTimeList() {
		return visitRepository.getVisitTimeList();
	}
}
