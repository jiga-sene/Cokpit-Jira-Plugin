package com.tc.labt.sgabs.cokpit.core.repo.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.commons.MessageError;
import com.tc.labt.sgabs.cokpit.core.entity.Account;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.AccountRepo;

@Component
public class AccountRepoImpl implements AccountRepo {

	@Autowired
	private AbstractCrudRepository<Account, Long> repoService;
	
	@Override
	public ErrorOrResult<Account> save(String subsidiary, Account account) {
		if(Objects.isNull(account))
			return ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT));
		
		ErrorOrResult<Account> result = null;
		try {
			Account accountSaved = this.repoService.saveOrUpdate(subsidiary, account);
			result = ErrorOrResult.ok(accountSaved);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
			result = ErrorOrResult.error(e, account);
		}
		return result;
	}

	@Override
	public List<ErrorOrResult<Account>> saveAll(String subsidiary, List<Account> accounts) {
		List<ErrorOrResult<Account>> accountsSaved = Collections.emptyList();
		if(Objects.isNull(accounts)) {
			accountsSaved.add(ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT)));
			return accountsSaved;
		}
		accounts.forEach(account -> accountsSaved.add(save(subsidiary, account)));
		return accountsSaved;
	}

	@Override
	public List<Account> findAllByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		List<Account> accounts = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("customer.codeClient", codeClient); 
			accounts = this.repoService.findWithCriteria(subsidiary, Account.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return accounts;
	}

}
