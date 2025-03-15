package com.tc.labt.sgabs.cokpit.core.servlet;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.google.common.collect.Maps;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.core.service.CustomerServiceImpl;

public class KYCFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String VM = "/vm/business/kycfile.vm";
	private static Logger log = LoggerFactory.getLogger(KYCFileServlet.class);
	
	private final UserManager userManager;
	@ComponentImport
	private final TemplateRenderer renderer;
	
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Inject
	public KYCFileServlet(@ComponentImport UserManager userManager, TemplateRenderer templateRenderer) {
		this.userManager = userManager;
		this.renderer = templateRenderer;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subsidiary = request.getParameter("subsidiary");
		String codeClient = request.getParameter("key");
		Map<String, Object> context = Maps.newHashMap();

		Customer kycFile = customerService.getFileKYC(subsidiary, codeClient);
		context.put("subsidiary", subsidiary);
		context.put("kycFile", kycFile);
		context.put("jissues", customerService.getIssuesDTO(subsidiary, codeClient));
		context.put("accounts", customerService.getAccountsForCustomer(subsidiary, codeClient));
		context.put("beneficiaries", customerService.getBeneficiariesDTO(subsidiary, codeClient));
		context.put("shareholders", customerService.getShareHoldersDTO(subsidiary, codeClient));
		context.put("leaders", customerService.getLeadersDTO(subsidiary, codeClient));
		renderer.render(VM, context, response.getWriter());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
