package com.tc.labt.sgabs.cokpit.permissions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.plugin.ProjectPermissionKey;
import com.atlassian.jira.user.ApplicationUser;
import com.tc.labt.sgabs.cokpit.configuration.property.PropertyManagement;
import com.tc.labt.sgabs.cokpit.util.ManagementUtil;

import lombok.Builder;

@Builder
public class JiraPermissionsConditionService implements PermissionsConditionService{
	
    private static final String BROWSE = "BROWSE_PROJECTS";
	
	@Override
	public boolean canBrowseProjects(String projectName, String username) {
		ApplicationUser user = ManagementUtil.getUserByName(username);
		return canBrowseProjects(projectName, user);
	}

	@Override
	public boolean canBrowseProjects(String projectName, ApplicationUser user) {
		Project project = ManagementUtil.getProject(projectName);
		Objects.requireNonNull(project, "Project cannot be null");
		return ComponentAccessor.getPermissionManager().hasPermission(new ProjectPermissionKey(BROWSE), project, user);
	}

	@Override
	public boolean hasAccessToCokpitv4(ApplicationUser user) {
        return ComponentAccessor.getGroupManager().getGroupNamesForUser(user.getUsername())
                .stream().anyMatch(group-> group.endsWith(PropertyManagement.getProperty().property.suffixGroup));
	}

	@Override
	public List<String> projectsAccessibleByUser(String username) {
		return ComponentAccessor.getGroupManager().getGroupNamesForUser(username)
				.stream().filter(group -> group.endsWith(PropertyManagement.getProperty().property.suffixGroup))
				.map(group -> group.split(" ")[0])
				.collect(Collectors.toList());
	}

}
