package com.shop.item.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import com.shop.item.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Long>,
		QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

	 @Query("select i from Item i where i.itemDetail like " +
	            "%:itemDetail% order by i.price desc")
	    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	 
	 @Query(value="select * from item i where i.item_detail like " +
	            "%:itemDetail% order by i.price desc", nativeQuery = true)
	    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
