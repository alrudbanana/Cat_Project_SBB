package com.shop.entity;

import javax.persistence.Column;

import org.springframework.data.annotation.LastModifiedBy;

public class BaseEntity extends BaseTimeEntity{
	
	@Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}
