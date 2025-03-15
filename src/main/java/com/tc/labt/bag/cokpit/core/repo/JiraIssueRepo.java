package com.tc.labt.sgabs.cokpit.core.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.core.entity.JiraIssue;

public interface JiraIssueRepo {
	
	public static final Logger log = LoggerFactory.getLogger(JiraIssueRepo.class);

	ErrorOrResult<JiraIssue> saveJiraIssue(JiraIssue jiraIssue);
	
	JiraIssue findJiraIssueByKey(String issuekey);
	List<JiraIssue> findAllJiraIssueByCodeClient(String subsidiary, String codeClient);
}
