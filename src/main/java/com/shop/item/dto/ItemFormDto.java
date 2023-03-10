package com.shop.item.dto;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

import com.shop.constant.ItemSellStatus;
import com.shop.item.entity.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();
    
    // Dto의 값을 Entity 클래스와 연결해서 자동으로 값이 주입 되도록 함 
    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item,ItemFormDto.class);
    }
}
