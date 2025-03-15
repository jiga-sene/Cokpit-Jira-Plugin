package com.tc.labt.sgabs.cokpit.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;

@Scanned
@Service
public class UserManagerServiceImpl implements UserManagerService{
	
	private final UserManager userManager;
	
	public UserManagerServiceImpl() {
		this.userManager = ComponentAccessor.getUserManager();
	}

	@Override
	public ApplicationUser getUserByKey(String userkey) {
		Objects.requireNonNull(userkey);
		return userManager.getUserByKey(userkey);
	}

}
