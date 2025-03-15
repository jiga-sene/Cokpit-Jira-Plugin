package com.tc.labt.sgabs.cokpit.core.dto.handler;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.labt.sgabs.cokpit.core.dto.AccountDTO;
import com.tc.labt.sgabs.cokpit.core.entity.Account;

public class AccountHandler {
	
	public static List<AccountDTO> retrieveAccount(List<Account> accounts){

		return accounts.parallelStream().map(account -> 
			AccountDTO.builder()
				.typeCompte(account.getTypeCompte())
				.libelleTypeCompte(account.getLibelleTypeCompte())
				.numeroCompte(account.getNumeroCompte())
				.clientNonResident(account.getClientNonResident())
				.dateOuverture(account.getDateOuverture())
				.dateFermeture(account.getDateFermeture())
				.compteFerme(account.getCompteFerme())
				.compteOuvertADistance(account.getCompteOuvertADistance())
				.contexteOuverture(account.getContexteOuverture())
				.motivationOuvertureADistance(account.getMotivationOuvertureADistance())
				.motifKYC(account.getMotifKYC())
				.build()
				).collect(Collectors.toList());
	}

}
