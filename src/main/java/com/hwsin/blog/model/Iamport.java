package com.hwsin.blog.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Iamport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //등록번호
	
	@Column(nullable = false,length = 100) // 결제번호
	private String imp_uid;
	
	@Column(nullable = false,length = 100) // 주문번호
	private String merchant_uid;
	
	@Column(nullable = false,length = 100) // 주문자 주소
	private String buyer_email;
	
	@Column(nullable = false,length = 100) // 주문자 우편번호
	private String buyer_postcode; 

	@Column(nullable = false,length = 100) // 상품명
	private String name;
	
	@Column(nullable = false,length = 100) // 결제금액
	private int amount;
}
