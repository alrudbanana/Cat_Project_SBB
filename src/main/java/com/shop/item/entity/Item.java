package com.shop.item.entity;





import com.shop.BaseEntity;
import com.shop.item.ItemSellStatus;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@ToString
@Getter @Setter
@Table(name="item")
public class Item extends BaseEntity{
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //아이템 숫자 
	
	@Column(nullable = false, length = 50)
	private String item_nm; //아이템 이름 
	
	@Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //아이템 판매현황 
	
	@Lob
	@Column(nullable = false)
	private String item_detail; //아이템 상세설명 
	
	@Column(name="price", nullable = false)
	private int price;//가격 
	 
	@Column(nullable = false)
	private int stock_number; //재고 
 
}
