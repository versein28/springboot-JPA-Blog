package com.hwsin.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hwsin.blog.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
	@Modifying
	@Query(value = "INSERT INTO Visit (date, visit_ip) VALUES (sysdate(), ?1)", nativeQuery = true)
	int setVisitTotalCount(String ip);
	
	/*
	 * @Query(value = "SELECT count(*) from Visit", nativeQuery = true) int
	 * getVisitTotalCount();
	 * 
	 * @Query(value = "SELECT count(*) FROM Visit WHERE DATE(date) = DATE(NOW())",
	 * nativeQuery = true) int getVisitTodayCount();
	 */
	
	@Query(value = "SELECT * FROM Visit WHERE date between DATE_SUB(NOW(), INTERVAL 7 DAY) and"
			+ " NOW() ORDER BY date", nativeQuery = true)
	List<Visit> getVisitTimeList();

}
