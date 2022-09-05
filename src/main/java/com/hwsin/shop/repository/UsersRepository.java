package com.hwsin.shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hwsin.shop.model.Users;

// DAO
// 자동으로 bean등록이 된다.
// @Repository // 생략 가능하다.
public interface UsersRepository extends JpaRepository<Users, Integer>{
	// SELECT * FROM user WHERE username = 1?;
	Optional<Users> findByUsername(String username);
	Optional<Users> findByUsernameAndEmail(String username, String email);
}


// JPA Naming 쿼리
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
//User findByUsernameAndPassword(String username, String password);

//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);