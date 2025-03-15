package com.tc.labt.sgabs.cokpit.core.api.spi;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.core.entity.Account;
import com.tc.labt.sgabs.cokpit.core.entity.Beneficiary;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.core.entity.JiraIssue;
import com.tc.labt.sgabs.cokpit.core.entity.Leader;
import com.tc.labt.sgabs.cokpit.core.entity.Pep;
import com.tc.labt.sgabs.cokpit.core.entity.ShareHolder;

@Service
@ExportAsService(HistorizationService.class)
public class HistorizationServiceImpl implements HistorizationService {

	@Override
	public ErrorOrResult<JiraIssue> saveJiraIssue(JiraIssue jiraIssue) {
		return null;
	}

	@Override
	public ErrorOrResult<Customer> addCustomerToJiraIssue(Customer customer, JiraIssue jiraIssue) {
		return null;
	}

	@Override
	public ErrorOrResult<Pep> addPepToCustomer(Pep pep, Customer customer) {
		return null;
	}

	@Override
	public List<ErrorOrResult<Account>> addAccountsToCustomer(List<Account> accounts, Customer customer) {
		return null;
	}

	@Override
	public List<ErrorOrResult<Beneficiary>> addBenefeciariesToCustomer(List<Beneficiary> beneficiaries,
			Customer customer) {
		return null;
	}

	@Override
	public List<ErrorOrResult<Leader>> addLeaderToCustomer(List<Leader> leaders, Customer customer) {
		return null;
	}

	@Override
	public List<ErrorOrResult<ShareHolder>> addShareHolderToCustomer(List<ShareHolder> shareHolders,
			Customer customer) {
		return null;
	}

}
