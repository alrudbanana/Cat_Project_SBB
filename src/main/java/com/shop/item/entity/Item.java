package com.shop.item.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="item")
public class Item {
	private Integer id; //아이템 숫자 
	private String item_nm; //아이템 이름 
	private String item_sell_status; //아이템 판매현황 
	private String item_detail; //아이템 상세설명 
	private int price;//가격 
	private int stock_number; //재고 
 
}
