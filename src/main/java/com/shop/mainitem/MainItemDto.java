package com.shop.mainitem;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {
	private Long id;
	private String itemNm;
	private String itemDetail;
	private String imgurl;
	private Integer price;
	
	@QueryProjection
	public MainItemDto(Long id) {
		this.id = id;
	}
}
