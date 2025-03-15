package com.tc.labt.sgabs.cokpit.core.dto.handler;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.labt.sgabs.cokpit.core.dto.BeneficiaryDTO;
import com.tc.labt.sgabs.cokpit.core.entity.Beneficiary;

public class BeneficiaryHandler {

	public static List<BeneficiaryDTO> retrieveBeneficiary(List<Beneficiary> beneficiaries){

		return beneficiaries.parallelStream().map(beneficiary -> 
				BeneficiaryDTO.builder()
					.code(beneficiary.getCode())
					.nomTiers(beneficiary.getNomTiers())
					.prenomTiers(beneficiary.getPrenomTiers()).build())
				.collect(Collectors.toList());
	}
}
