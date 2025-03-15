package com.tc.labt.sgabs.cokpit.permissions;

import java.util.List;

import com.atlassian.jira.user.ApplicationUser;

public interface PermissionsConditionService {
	
    boolean canBrowseProjects(String projectName, String username);

    boolean canBrowseProjects(String projectName, ApplicationUser user);
    
	boolean hasAccessToCokpitv4(ApplicationUser user);
	
	List<String> projectsAccessibleByUser(String username);

}
