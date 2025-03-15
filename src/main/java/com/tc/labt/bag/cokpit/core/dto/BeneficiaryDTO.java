package com.tc.labt.sgabs.cokpit.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data @AllArgsConstructor
public class BeneficiaryDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String code;
	String nomTiers;
	String prenomTiers;
}
