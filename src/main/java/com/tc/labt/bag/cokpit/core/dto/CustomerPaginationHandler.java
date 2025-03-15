package com.tc.labt.sgabs.cokpit.core.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;

public class CustomerPaginationHandler {
	
	public static CustomerPaginationDTO retrieveCustomerPagination(Customer customer) {

		return CustomerPaginationDTO.builder()
			.codeClient(Strings.nullToEmpty(customer.getCodeClient()))
			.displayName(Strings.nullToEmpty(customer.getDisplayName()))
			.libelleTypeClient(Strings.nullToEmpty(customer.getLibelleTypeClient()))
			.statutKYC(Strings.nullToEmpty(customer.getStatutKYC()))
			.segmentClient(Strings.nullToEmpty(customer.getSegmentClient()))
			.classificationRisque(Strings.nullToEmpty(customer.getClassificationRisque()))
			.avisRCOFatca(Strings.nullToEmpty(customer.getAvisRCOFatca()))
			.flagPEP(Strings.nullToEmpty(customer.getFlagPEP()))
			.build();
	}
	
	public static List<CustomerPaginationDTO> retrieveCustomersPagination(List<Customer> customers){
		List<CustomerPaginationDTO> customersP = customers.parallelStream().map(CustomerPaginationHandler::retrieveCustomerPagination).collect(Collectors.toList());
		return customersP;
	}
}
