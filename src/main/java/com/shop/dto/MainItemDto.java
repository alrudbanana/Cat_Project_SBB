package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainItemDto {
//메인화면에 출력할 데이터를 위한 DTO객체, 사용자에게 보여질 내용만 포함
	private Long id;
	private String itemName;
	private String itemDetail;
	private String imgUrl;
	private String price;
	
	@QueryProjection
	public MainItemDto(Long id, String itemName, String itemDetail, 
			String imgUrl,  String price ) {
		this.id = id;
		this.itemName = itemName;
		this.itemDetail = itemDetail;
		this.imgUrl = imgUrl;
		this.price = price;
	}
	
}
