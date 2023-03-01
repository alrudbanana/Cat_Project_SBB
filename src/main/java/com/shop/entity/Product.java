package com.shop.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.shop.constant.ItemSellStatus;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@NoArgsConstructor 
@Getter @Setter
@Entity @Table(name = "product")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
	private Long prod_id; //상품코드
	
	@Column(nullable = false, length = 50)
	private String prod_name; //상품명
	
	@Column(name = "price", nullable = false)
	private int price; //상품가격
	
	@Lob
	@Column(nullable = false)
	private String description; //상품상세설명
	
	
	@NotNull(message = "상품이미지를 넣어야합니다.")
	private String img_url; //상품 이미지 url
	
	@NotNull(message = "상품 색을 지정해야 합니다.")
	private String color; //상품 색
	
	@NotNull(message = "상품사이지를 지정해야합니다.")
	private String size; //상품사이즈
	private int discount; //할인율
	
	@NotNull(message = "상품재고를 설정해야 합니다.")
	private int stockNumber; //재고 
	
	@Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //제품판매상태
	
	@Builder //해당 클래스의 빌더 패턴 클래스 생성, 빌더를 사용하면 어떤필드에 값을 채울지 명확하게 인지 가능 
	public Product(String prod_name, int price, int stockNumber, 
			String description, String img_url, String size, int discount, 
			ItemSellStatus itemSellStatus) {
		this.prod_name = prod_name; //상품명
		this.price = price; //가격
		this.description = description; //상세설명
		this.img_url = img_url; //사진url
		this.size = size; //사이즈
		this.discount = discount; //할인
		this.stockNumber = stockNumber; //재고 
 		this.itemSellStatus = itemSellStatus; // 판매중 , 품절 
		
	}
	
	
	
}
