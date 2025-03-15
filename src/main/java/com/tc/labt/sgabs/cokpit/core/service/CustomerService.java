package com.tc.labt.sgabs.cokpit.core.service;

import java.util.List;

import com.tc.labt.sgabs.cokpit.core.dto.AccountDTO;
import com.tc.labt.sgabs.cokpit.core.dto.BeneficiaryDTO;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPaginationDTO;
import com.tc.labt.sgabs.cokpit.core.dto.JiraIssueDTO;
import com.tc.labt.sgabs.cokpit.core.dto.LeaderDTO;
import com.tc.labt.sgabs.cokpit.core.dto.ShareHolderDTO;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.datatable.DataTableModel;
import com.tc.labt.sgabs.cokpit.datatable.Pagination;

public interface CustomerService {
	List<Customer> retrieveDataTEST(String subsidiary);

	Customer getFileKYC(String subsidiary, String codeClient);
	
	List<AccountDTO> getAccountsForCustomer(String subsidiary, String codeClient);
	
	List<JiraIssueDTO> getIssuesDTO(String subsidiary, String codeClient);
	
	Pagination<CustomerPaginationDTO> retrieveDataPagination(String subsidiary, DataTableModel dataTableModel);
	
	List<BeneficiaryDTO> getBeneficiariesDTO(String subsidiary, String codeClient);
	List<ShareHolderDTO> getShareHoldersDTO(String subsidiary, String codeClient);
	List<LeaderDTO> getLeadersDTO(String subsidiary, String codeClient);
}
