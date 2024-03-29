package com.shop.item.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.item.dto.ItemFormDto;
import com.shop.item.dto.ItemSearchDto;
import com.shop.item.entity.Item;
import com.shop.item.service.ItemService;
import com.shop.mainitem.MainItemDto;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ItemController {
	
	private final ItemService itemService;
	//상품등록 폼 전달 
	@GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/ItemForm";
	}
	
	//상품 등록 처리 
	 @PostMapping(value = "/admin/item/new")
	    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
	                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){

	        if(bindingResult.hasErrors()){
	            return "item/ItemForm";
	        }

	        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
	            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
	            return "item/ItemForm";
	        }

	        try {
	            itemService.saveItem(itemFormDto, itemImgFileList);
	        } catch (Exception e){
	            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
	            return "item/ItemForm";
	        }

	        return "redirect:/";
	    }
	 
	 //상세보기 메소드
	  @GetMapping(value = "/admin/item/{itemId}")
	    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){

	        try {
	            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
	            model.addAttribute("itemFormDto", itemFormDto);
	        } catch(EntityNotFoundException e){
	            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
	            model.addAttribute("itemFormDto", new ItemFormDto());
	            return "item/itemForm";
	        }

	        return "item/itemForm";
	    }
	  
	  //수정하기 
	  @PostMapping(value = "/admin/item/{itemId}")
	    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
	                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model){
	        if(bindingResult.hasErrors()){
	            return "item/itemForm";
	        }

	        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
	            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
	            return "item/itemForm";
	        }

	        try {
	            itemService.updateItem(itemFormDto, itemImgFileList);
	        } catch (Exception e){
	            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
	            return "item/itemForm";
	        }

	        return "redirect:/";
	    }
	  
	  @GetMapping(value = {"/admin/items", "/admin/items/{page}"}) //페이지 번호가 없는 경우와 있는 경우 두가지 맵핑
	    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
	        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() :  0, 10); //한 페이지당 10개씩 상품을 보여줌 
	        Page<Item> items = 
	        		itemService.getAdminItemPage(itemSearchDto, pageable);
	        model.addAttribute("items", items);
	        model.addAttribute("itemSearchDto", itemSearchDto);
	        model.addAttribute("maxPage", 5);

	        return "item/itemMng";
	    }

	  	//상품리스트로 들어감
	      @GetMapping(value = "item/ItemList")
	      public String main(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
	          Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 8);
	          Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
	          model.addAttribute("items", items);
	          model.addAttribute("itemSearchDto", itemSearchDto);
	          model.addAttribute("maxPage", 5);

	          return "item/ItemList";
	      }
}


