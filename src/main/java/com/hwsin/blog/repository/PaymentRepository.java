package com.hwsin.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.blog.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Page<Payment> findByUserId(int userId, Pageable pageable);
	//선택된 날짜에 대한 결제내역 조회
	@Query(value = "SELECT * FROM Payment WHERE userId = ?1 AND order_date between ?2 and ?3", nativeQuery = true)
	Page<Payment> findBySelectedDate(int userId, String fromDate, String toDate, Pageable pageable);
}