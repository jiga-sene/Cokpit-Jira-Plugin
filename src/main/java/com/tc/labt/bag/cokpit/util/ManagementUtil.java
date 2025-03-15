package com.tc.labt.sgabs.cokpit.util;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.util.JiraHome;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;

public class ManagementUtil {
	
    public final static String SEPARATOR_PATH = "/";
    public final static String PREFIX_PROJECT = "SG";
    public final static String ERROR_TITLE = "COKPIT PLUGIN: ";

    public static String getJiraHome(){
        JiraHome jiraHome = ComponentAccessor.getComponentOfType(JiraHome.class);
        return jiraHome.getHomePath().concat(SEPARATOR_PATH);
    }

	public static List<String> getAllProject() {
        return ComponentAccessor.getProjectManager().getProjectObjects()
        		.parallelStream().filter(project -> project.getKey().startsWith(ManagementUtil.PREFIX_PROJECT))
        		.map(Project::getKey).collect(Collectors.toList());
    }

	public static ApplicationUser getUserByName(String username) {
		Objects.requireNonNull(username, "Username cannot be null");
		return ComponentAccessor.getUserManager().getUserByKey(username);
	}
	
    public static Project getProject(String projectName){
        return ComponentAccessor.getProjectManager().getProjectObjByKey(projectName);
    }
}
