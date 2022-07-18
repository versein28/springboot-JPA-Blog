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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length = 100) // 상품명
	private String prodName;
	
	@Column(nullable = false,length = 100) // 브랜드 명
	private String prodBrand;
	
	@Lob//대용량 데이터 - 설명
	private String content; 

	@Column(nullable = false,length = 100) // 상품 가격
	private String prodKrw;
	
	private int count;//조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Product, User=One
	@JoinColumn(name="userId")
	private Users user;//DB는 오브제그를 저장할 수 없다. FK,자바는 오브젝트를 저장할 수 있다.
	
    @Column(columnDefinition = "TEXT")
    private String filePath;
    
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
