package com.shop.item.entity;


import com.shop.BaseEntity;
import com.shop.constant.ItemSellStatus;
import com.shop.item.dto.ItemFormDto;

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
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
@Table(name="item")
public class Item extends BaseEntity{
	@Id
	@Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //아이템 숫자 
	
	@Column(nullable = false, length = 50)
	private String itemNm; //아이템 이름 
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemsellstatus; //아이템 판매현황
	
	@Lob
	@Column(nullable = false)
	private String itemDetail; //아이템 상세설명
	
	@Column(name="price", nullable = false)
	private int price;//가격 
	private int stockNumber; //재고 
 
	
	
	public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemsellstatus = itemFormDto.getItemSellStatus();
    }
}
