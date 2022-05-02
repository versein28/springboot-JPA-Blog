package com.hwsin.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hwsin.blog.model.Board;
import com.hwsin.blog.model.Users;


public interface BoardRepository extends JpaRepository<Board, Integer>{

}

