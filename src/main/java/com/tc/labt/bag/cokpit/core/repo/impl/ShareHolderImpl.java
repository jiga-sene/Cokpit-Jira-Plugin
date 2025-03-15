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
import com.tc.labt.sgabs.cokpit.core.entity.ShareHolder;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.ShareHolderRepo;

@Component
public class ShareHolderImpl implements ShareHolderRepo {

	@Autowired
	private AbstractCrudRepository<ShareHolder, Long> repoService;

	@Override
	public ErrorOrResult<ShareHolder> save(String subsidiary, ShareHolder shareHolder) {
		if(Objects.isNull(shareHolder))
			return ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT));
		
		ErrorOrResult<ShareHolder> result = null;
		try {
			ShareHolder shareHolderSaved = this.repoService.saveOrUpdate(subsidiary, shareHolder);
			result = ErrorOrResult.ok(shareHolderSaved);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
			result = ErrorOrResult.error(e, shareHolder);
		}
		return result;
	}

	@Override
	public List<ErrorOrResult<ShareHolder>> saveAll(String subsidiary, List<ShareHolder> shareHolders) {
		List<ErrorOrResult<ShareHolder>> shareHoldersSaved = Collections.emptyList();
		if(Objects.isNull(shareHolders)) {
			shareHoldersSaved.add(ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT)));
			return shareHoldersSaved;
		}
		shareHolders.forEach(shareHolder-> shareHoldersSaved.add(save(subsidiary, shareHolder)));
		return shareHoldersSaved;
	}

	@Override
	public List<ShareHolder> findAllByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		List<ShareHolder> shareHolders = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("customer.codeClient", codeClient); 
			shareHolders = this.repoService.findWithCriteria(subsidiary, ShareHolder.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return shareHolders;
	}

}
