package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Product;

public interface ItemRepository extends JpaRepository<Product, Long> {

}
