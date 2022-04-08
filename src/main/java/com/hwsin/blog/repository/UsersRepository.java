package com.hwsin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwsin.blog.model.Users;

//DAO
//자동으로 bean등록이 된다
//@Repository생략가능
public interface UsersRepository extends JpaRepository<Users, Integer> {

	
}
