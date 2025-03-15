package com.tc.labt.sgabs.cokpit.core.repo;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPaginationDTO;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.datatable.Pagination;

public interface CustomerRepo {
	
	public static final Logger log = LoggerFactory.getLogger(CustomerRepo.class);

	ErrorOrResult<Customer> saveCustomer(String subsidiary, Customer customer);
	
	Customer findCustomerByCodeClient(String subsidiary, String codeClient);
	
	List<Customer> retrieveDataTEST(String subsidiary);
	Pagination<CustomerPaginationDTO> retrieveDataPagination(String subsidiary, int page, int size, Criterion criterion);
}
