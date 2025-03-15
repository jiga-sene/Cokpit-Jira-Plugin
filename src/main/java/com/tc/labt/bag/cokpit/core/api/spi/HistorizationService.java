package com.tc.labt.sgabs.cokpit.core.api.spi;

import java.util.List;

import com.atlassian.annotations.PublicSpi;
import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.core.entity.Account;
import com.tc.labt.sgabs.cokpit.core.entity.Beneficiary;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.core.entity.JiraIssue;
import com.tc.labt.sgabs.cokpit.core.entity.Leader;
import com.tc.labt.sgabs.cokpit.core.entity.Pep;
import com.tc.labt.sgabs.cokpit.core.entity.ShareHolder;

@PublicSpi
public interface HistorizationService {
	
	
	public ErrorOrResult<JiraIssue> saveJiraIssue(JiraIssue jiraIssue);
	
	public ErrorOrResult<Customer> addCustomerToJiraIssue(Customer customer, JiraIssue jiraIssue);
	public ErrorOrResult<Pep> addPepToCustomer(Pep pep, Customer customer);
	
	public List<ErrorOrResult<Account>> addAccountsToCustomer(List<Account> accounts, Customer customer);
	public List<ErrorOrResult<Beneficiary>> addBenefeciariesToCustomer(List<Beneficiary> beneficiaries, Customer customer);
	public List<ErrorOrResult<Leader>> addLeaderToCustomer(List<Leader> leaders, Customer customer);
	public List<ErrorOrResult<ShareHolder>> addShareHolderToCustomer(List<ShareHolder> shareHolders, Customer customer);
}
