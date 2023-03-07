package com.shop.member.entity;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {
	private String zipcode;
	private String streetAdr;
	private String detailAdr;
	
	public Address() {}
	
	public Address(String zipcode, String streetAdr, String detailAdr) {
		this.zipcode = zipcode;
		this.streetAdr = streetAdr;
		this.detailAdr = detailAdr;
	}

}
