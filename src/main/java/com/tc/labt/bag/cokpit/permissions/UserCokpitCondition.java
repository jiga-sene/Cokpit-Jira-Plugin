package com.tc.labt.sgabs.cokpit.permissions;

import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

public class UserCokpitCondition extends AbstractWebCondition{

	@Override
	public boolean shouldDisplay(ApplicationUser applicationUser, JiraHelper jiraHelper) {
		PermissionsConditionService permissionsConditionService = JiraPermissionsConditionService.builder().build();
		return permissionsConditionService.hasAccessToCokpitv4(applicationUser);
	}

}
