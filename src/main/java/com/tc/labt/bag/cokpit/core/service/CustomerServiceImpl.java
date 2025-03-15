package com.tc.labt.sgabs.cokpit.core.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.core.dto.AccountDTO;
import com.tc.labt.sgabs.cokpit.core.dto.BeneficiaryDTO;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPagination;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPaginationDTO;
import com.tc.labt.sgabs.cokpit.core.dto.JiraIssueDTO;
import com.tc.labt.sgabs.cokpit.core.dto.LeaderDTO;
import com.tc.labt.sgabs.cokpit.core.dto.ShareHolderDTO;
import com.tc.labt.sgabs.cokpit.core.dto.handler.AccountHandler;
import com.tc.labt.sgabs.cokpit.core.dto.handler.BeneficiaryHandler;
import com.tc.labt.sgabs.cokpit.core.dto.handler.JiraIssueHandler;
import com.tc.labt.sgabs.cokpit.core.dto.handler.LeaderHandler;
import com.tc.labt.sgabs.cokpit.core.dto.handler.ShareHolderHandler;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.core.repo.AccountRepo;
import com.tc.labt.sgabs.cokpit.core.repo.BeneficiaryRepo;
import com.tc.labt.sgabs.cokpit.core.repo.CustomerRepo;
import com.tc.labt.sgabs.cokpit.core.repo.JiraIssueRepo;
import com.tc.labt.sgabs.cokpit.core.repo.LeaderRepo;
import com.tc.labt.sgabs.cokpit.core.repo.ShareHolderRepo;
import com.tc.labt.sgabs.cokpit.datatable.DataTableModel;
import com.tc.labt.sgabs.cokpit.datatable.Pagination;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private JiraIssueRepo jIssueRepo;
	@Autowired
	private BeneficiaryRepo beneficiaryRepo;
	@Autowired
	private ShareHolderRepo shareHolderRepo;
	@Autowired
	private LeaderRepo leaderRepo;
	
	@Override
	public Customer getFileKYC(String subsidiary, String codeClient) {
		//KycFileDTO.builder().codeClient(codeClient).typeClient("PP").displayName("Nom 000").status("Annulé").build();
		return customerRepo.findCustomerByCodeClient(subsidiary, codeClient);
	}
	
	@Override
	public List<Customer> retrieveDataTEST(String subsidiary){
		
		List<Customer> customers = customerRepo.retrieveDataTEST(subsidiary);
		return customers;
	}
	
	@Override
	public Pagination<CustomerPaginationDTO> retrieveDataPagination(String subsidiary, DataTableModel dataTableModel){
		
		Pagination<CustomerPaginationDTO> customers = customerRepo.retrieveDataPagination(subsidiary, dataTableModel.start, dataTableModel.length, CustomerPagination.buildCriterion(dataTableModel));
		return customers;
	}

	@Override
	public List<AccountDTO> getAccountsForCustomer(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return Collections.emptyList();
		return AccountHandler.retrieveAccount(accountRepo.findAllByCodeClient(subsidiary, codeClient));
	}

	@Override
	public List<JiraIssueDTO> getIssuesDTO(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return Collections.emptyList();
		return JiraIssueHandler.retrieveJiraIssues(jIssueRepo.findAllJiraIssueByCodeClient(subsidiary, codeClient));
	}

	@Override
	public List<BeneficiaryDTO> getBeneficiariesDTO(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return Collections.emptyList();
		return BeneficiaryHandler.retrieveBeneficiary(beneficiaryRepo.findAllByCodeClient(subsidiary, codeClient));
	}

	@Override
	public List<ShareHolderDTO> getShareHoldersDTO(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return Collections.emptyList();
		return ShareHolderHandler.retrieveShareHolder(shareHolderRepo.findAllByCodeClient(subsidiary, codeClient));
	}

	@Override
	public List<LeaderDTO> getLeadersDTO(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return Collections.emptyList();
		return LeaderHandler.retrieveLeader(leaderRepo.findAllByCodeClient(subsidiary, codeClient));
	}
	
}
