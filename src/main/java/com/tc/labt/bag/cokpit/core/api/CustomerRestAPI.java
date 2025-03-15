package com.tc.labt.sgabs.cokpit.core.api;

import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPaginationDTO;
import com.tc.labt.sgabs.cokpit.core.service.CustomerService;
import com.tc.labt.sgabs.cokpit.datatable.DataTableModel;
import com.tc.labt.sgabs.cokpit.datatable.Pagination;

@Scanned
@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRestAPI {

	private final UserManager userManager;
	
	@Autowired
	private CustomerService customerService;
	
	@Inject
	public CustomerRestAPI(@ComponentImport UserManager userManager) {
		this.userManager = userManager;
	}
	
	@GET
	@Path("{subsidiary}/customer/{codeClient}")
	public Response getClient(final @PathParam("subsidiary") String subsidiary, final @PathParam("codeClient") String codeClient) {
		return Response.ok(customerService.getFileKYC(subsidiary, codeClient)).build();
	}
	
	@GET
	@Path("{subsidiary}/filter/test")
	public Response filterCustomers(final @PathParam("subsidiary") String subsidiary) {
		return Response.ok(customerService.retrieveDataTEST(subsidiary)).build();
	}
	
	@POST
	@Path("/{subsidiary}/filter")
	public Response filterCustomers(final @PathParam("subsidiary") String subsidiary, final DataTableModel dataTableModel,
			@Context HttpServletRequest request){

		Objects.requireNonNull(subsidiary, "Subsidiary is null");
		Objects.requireNonNull(dataTableModel, "DataTableModel uncorrectly filled");
		
		Pagination<CustomerPaginationDTO> customersPagination = customerService.retrieveDataPagination(subsidiary, dataTableModel);
		customersPagination.setDraw(dataTableModel.draw);
		return Response.ok(customersPagination).build();
	}
	
}
