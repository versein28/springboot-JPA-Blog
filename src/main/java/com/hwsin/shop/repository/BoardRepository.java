package com.hwsin.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hwsin.shop.model.Board;
import com.hwsin.shop.model.Users;


public interface BoardRepository extends JpaRepository<Board, Integer>{

	  @Modifying    
	  @Query("update Board b set b.count = b.count + 1 where b.id = ?1")    
	  void increaseViewCount(int id);
}

