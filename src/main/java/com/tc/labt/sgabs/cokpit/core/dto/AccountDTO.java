package com.tc.labt.sgabs.cokpit.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class AccountDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String numeroCompte;
	
	public String typeCompte;
	public String libelleTypeCompte;
	public String dateOuverture;
}
