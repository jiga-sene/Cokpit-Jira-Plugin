package com.tc.labt.sgabs.cokpit.core.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.tc.labt.sgabs.cokpit.permissions.JiraPermissionsConditionService;

@Named
@Scanned
public class HomePageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7603616452285631174L;
	
	private static final String VM = "/vm/business/homepage.vm";
    private static final String INPUT_SUBSIDIARY = "subsidiary";
	
	
	private final UserManager userManager;
	private final TemplateRenderer renderer;
	Map<String, Object> context;
	
	@Inject
	public HomePageServlet(/*LoginUriProvider loginUriProvider,*/ @ComponentImport UserManager userManager, @ComponentImport TemplateRenderer templateRenderer) {
//		super(loginUriProvider, userManager);
		this.renderer = templateRenderer;
		this.userManager = userManager;
		context = Maps.newHashMap();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = userManager.getRemoteUser(request).getUsername();
		context.put("subsidiaries", JiraPermissionsConditionService.builder().build().projectsAccessibleByUser(username));
		context.put("subsidiary", Strings.nullToEmpty(request.getParameter(INPUT_SUBSIDIARY)));
		renderer.render(VM, context, response.getWriter());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
