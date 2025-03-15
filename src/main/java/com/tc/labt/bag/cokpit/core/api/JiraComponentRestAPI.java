package com.tc.labt.sgabs.cokpit.core.api;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import com.tc.labt.sgabs.cokpit.permissions.JiraPermissionsConditionService;

@Scanned
@Path("/jira")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JiraComponentRestAPI {
	
	private final UserManager userManager;
	
	@Inject
	public JiraComponentRestAPI(@ComponentImport UserManager userManager) {
		this.userManager = userManager;
	}

	@GET
	@Path("/projects")
	public List<String> getProjectsAccessibleForUser(@Context HttpServletRequest request) {
		String username = userManager.getRemoteUser(request).getUsername();
		return JiraPermissionsConditionService.builder().build().projectsAccessibleByUser(username);
	}
	
}

