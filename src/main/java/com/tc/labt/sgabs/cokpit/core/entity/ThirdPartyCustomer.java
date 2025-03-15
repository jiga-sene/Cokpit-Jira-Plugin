package com.tc.labt.sgabs.cokpit.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass

@Data
public class ThirdPartyCustomer {

	@Id
	private String id;
	private String code;
	
	private String nomTiers;
	private String prenomTiers;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer", updatable = false)
	private Customer customer;
	
}
