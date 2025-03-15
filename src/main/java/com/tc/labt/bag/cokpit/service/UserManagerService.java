package com.tc.labt.sgabs.cokpit.service;

import com.atlassian.jira.user.ApplicationUser;

public interface UserManagerService {
	
	ApplicationUser getUserByKey(String userkey);
}
