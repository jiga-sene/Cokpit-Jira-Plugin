package com.tc.labt.sgabs.cokpit.core.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.datatable.Column;
import com.tc.labt.sgabs.cokpit.datatable.DataTableModel;
import com.tc.labt.sgabs.cokpit.datatable.Pagination;

public class CustomerPagination {

	static List<CustomerPaginationDTO> customers;

	
	public Pagination<CustomerPaginationDTO> getCustomersDTO(DataTableModel dataTableModel){
		Pagination<CustomerPaginationDTO> customersDTO = new Pagination<CustomerPaginationDTO>();
		customersDTO.draw=dataTableModel.draw;
		customersDTO.recordsFiltered=customers.size();
		customersDTO.recordsTotal=customers.size();
		customersDTO.data = customers;
		return customersDTO;
	}
	
	public static Criterion buildCriterion(DataTableModel dataTableModel) {
		
        String searchValue = dataTableModel.getSearch().getValue().trim();

        List<Column> columnsFiltered = dataTableModel.getColumns().stream()
                .filter(column -> column.isSearchable() && (!Strings.isNullOrEmpty(column.getSearch().getValue().trim()) || !Strings.isNullOrEmpty(searchValue)))
                .collect(Collectors.toList());
        Junction junction = Strings.isNullOrEmpty(searchValue) ? Restrictions.conjunction() : Restrictions.disjunction();

        columnsFiltered.forEach(column -> {
	        junction.add(Restrictions.like(column.getData(),
	                "%" + (Strings.isNullOrEmpty(column.getSearch().getValue().trim()) ? searchValue : column.getSearch().getValue().trim()) + "%").ignoreCase()
	        		);
        });
        return junction;
	}
}
