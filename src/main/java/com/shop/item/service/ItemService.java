package com.shop.item.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shop.board.Board;
import com.shop.item.dto.ItemFormDto;
import com.shop.item.dto.ItemImgDto;
import com.shop.item.dto.ItemSearchDto;
import com.shop.item.entity.Item;
import com.shop.item.entity.ItemImg;
import com.shop.item.repository.ItemImgRepository;
import com.shop.item.repository.ItemRepository;
import com.shop.mainitem.MainItemDto;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemImgService itemImgService; //이미지 정보를 저장 
	private final ItemImgRepository itemImgRepository; 
	 
	public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);
       
        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
	
	//수정메소드
	@Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }
	
	 public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
	        //상품 수정
	        Item item = itemRepository.findById(itemFormDto.getId())
	                .orElseThrow(EntityNotFoundException::new);
	        item.updateItem(itemFormDto);
	        List<Long> itemImgIds = itemFormDto.getItemImgIds();

	        //이미지 등록
	        for(int i=0;i<itemImgFileList.size();i++){
	            itemImgService.updateItemImg(itemImgIds.get(i),
	                    itemImgFileList.get(i));
	        }

	        return item.getId();
	    }
	 
	//상품데이터 조회 메소드 추가 (*데이터의 수정이 일어나지 않으므로 읽기전용)
	 @Transactional(readOnly = true)
	    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
	        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
	    } 
	 
	 //메인아이템 조회 메소드 
	 @Transactional(readOnly = true)
	    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
	        return itemRepository.getMainItemPage(itemSearchDto, pageable);
	    }
}
