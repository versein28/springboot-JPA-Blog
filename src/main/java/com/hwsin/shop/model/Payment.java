package com.hwsin.shop.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
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
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //등록번호
	
	@Column(nullable = false,length = 100) // 결제번호
	private String impUid;
	
	@Column(nullable = false,length = 100) // 주문번호
	private String merchantUid;
	
	@Column(nullable = false,length = 100) // 배송지 주소
	private String buyerAddr;
	
	@Column(nullable = false,length = 100) // 주문량
	private int qty;
	
	@Column(nullable = false,length = 100) // 총 결제금액
	private int amount;
	
	@Column(nullable = false,length = 100) // 주문자 전화번호
	private String buyerTel;
	
	@ManyToOne(fetch = FetchType.LAZY) // Many = 주문, One = 구매자
	@JoinColumn(name="userId")
	private Users user;//DB는 오브제그를 저장할 수 없다. FK,자바는 오브젝트를 저장할 수 있다.
	
	@ManyToOne(fetch = FetchType.LAZY) // Many = 주문, One = 상품
	@JoinColumn(name="prodId")
	private Product product;//DB는 오브제그를 저장할 수 없다. FK,자바는 오브젝트를 저장할 수 있다.
	
	@Temporal(TemporalType.DATE)//결제일
	@CreationTimestamp
	private Date orderDate;
}
