package com.hwsin.shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.shop.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Optional<Payment> findByMerchantUid(String MerchantUid);
	Optional<Payment> findByUserId(int userId);
	Page<Payment> findByUserId(int userId, Pageable pageable);
	@Query(value = "SELECT * FROM Payment WHERE userId = ?1 AND orderDate between ?2 and ?3", nativeQuery = true)
	Page<Payment> findBySelectedDate(int userId, String fromDate, String toDate, Pageable pageable);
}