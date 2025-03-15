package com.tc.labt.sgabs.cokpit.core.dto.handler;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.labt.sgabs.cokpit.core.dto.LeaderDTO;
import com.tc.labt.sgabs.cokpit.core.entity.Leader;

public class LeaderHandler {

	public static List<LeaderDTO> retrieveLeader(List<Leader> leaders){
		
		return leaders.parallelStream().map(leader -> 
			LeaderDTO.builder()
				.code(leader.getCode())
				.nomTiers(leader.getNomTiers())
				.prenomTiers(leader.getPrenomTiers())
				.resultatControlEmbargoSanctions(leader.getResultatControlEmbargoSanctions())
				.resiliationControlePPEOCR(leader.getResiliationControlePPEOCR())
				.indicateurPPE(leader.getIndicateurPPE())
				.build())
				.collect(Collectors.toList());
	}
}
