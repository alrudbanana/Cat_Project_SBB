package com.shop.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.shop.item.dto.ItemSearchDto;
import com.shop.item.entity.Item;

public interface ItemRepositoryCustom {
	//상품 조회 조건을 담고 있는 ItemSearchDto, 페이징 정보를 담고있는 paging 객체를 파라미터로 받는 getAdmin 메소드 선언 
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}