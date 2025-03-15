package com.tc.labt.sgabs.cokpit.core.repo.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlassian.jira.issue.IssueKey;
import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.commons.MessageError;
import com.tc.labt.sgabs.cokpit.core.entity.JiraIssue;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.JiraIssueRepo;

@Component
public class JiraIssueRepoImpl implements JiraIssueRepo{

	@Autowired
	private AbstractCrudRepository<JiraIssue, Long> repoService;
	
	@Override
	public ErrorOrResult<JiraIssue> saveJiraIssue(JiraIssue jiraIssue) {
		if(Objects.isNull(jiraIssue) || Strings.isNullOrEmpty(jiraIssue.getSubsidiary()))
			return ErrorOrResult.error(MessageError.build(MessageError.TITLE_ERROR + MessageError.NULL_OBJECT));
		
		ErrorOrResult<JiraIssue> result = null;
		try {
			JiraIssue jIssueSaved = this.repoService.saveOrUpdate(jiraIssue.getSubsidiary(), jiraIssue);
			result = ErrorOrResult.ok(jIssueSaved);
		} catch (Exception e) {
			result = ErrorOrResult.error(e, jiraIssue);
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return result;
	}

	@Override
	public JiraIssue findJiraIssueByKey(String issuekey) {
		if(IssueKey.isValidKey(issuekey))
			return null;
		List<JiraIssue> jIssues = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("issuekey", issuekey);
			jIssues = this.repoService.findWithCriteria(issuekey, JiraIssue.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return jIssues.get(0);
	}

	@Override
	public List<JiraIssue> findAllJiraIssueByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		List<JiraIssue> jIssues = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("codeClient", codeClient);
			jIssues = this.repoService.findWithCriteria(subsidiary, JiraIssue.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return jIssues;
	}

}
