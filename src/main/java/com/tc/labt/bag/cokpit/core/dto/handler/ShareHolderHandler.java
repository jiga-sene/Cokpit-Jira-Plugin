package com.tc.labt.sgabs.cokpit.core.dto.handler;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.labt.sgabs.cokpit.core.dto.ShareHolderDTO;
import com.tc.labt.sgabs.cokpit.core.entity.ShareHolder;

public class ShareHolderHandler {
	
	public static List<ShareHolderDTO> retrieveShareHolder(List<ShareHolder> shareHolders){

		return shareHolders.parallelStream().map(shareHolder -> 
				ShareHolderDTO.builder()
					.code(shareHolder.getCode())
					.nomTiers(shareHolder.getNomTiers())
					.prenomTiers(shareHolder.getPrenomTiers()).build())
				.collect(Collectors.toList());
	}
}
