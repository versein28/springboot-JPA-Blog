package com.hwsin.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // GenerationType.IDENTIFY 프로젝트에 연결된 DB의 넘버링 전략을 따라간다 -> tibero는?
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;

	@Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	//DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;// Enum을 쓰는게 좋다. //admin, user, manager <---- 해당 3가지의 도메인으로 정해진다
	
	@CreationTimestamp // 시간이 자동 입력
	private LocalDateTime createDate;
}
