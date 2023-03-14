package com.shop.mainitem;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class MainItemDto {
	private Long id;
	private String itemNm;
	private String itemDetail;
	private String imgUrl;
	private Integer price;
	
	@QueryProjection
	public MainItemDto(Long id, String itemNm, String itemDetail, String imgUrl, Integer price) {
		this.id = id;
		this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
	}
}
