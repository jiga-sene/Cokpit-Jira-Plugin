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
import com.tc.labt.sgabs.cokpit.core.entity.Pep;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.PepRepo;

@Component
public class PepRepoImpl implements PepRepo{

	@Autowired
	private AbstractCrudRepository<Pep, Long> repoService;
	
	@Override
	public ErrorOrResult<Pep> save(String subsidiary, Pep pep) {
		if(Objects.isNull(pep))
			return ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT));
		
		ErrorOrResult<Pep> result = null;
		try {
			Pep pepSaved = this.repoService.saveOrUpdate(subsidiary, pep);
			result = ErrorOrResult.ok(pepSaved);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
			result = ErrorOrResult.error(e, pep);
		}
		return result;
	}

	@Override
	public List<ErrorOrResult<Pep>> saveAll(String subsidiary, List<Pep> peps) {
		List<ErrorOrResult<Pep>> pepsSaved = Collections.emptyList();
		if(Objects.isNull(peps)) {
			pepsSaved.add(ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT)));
			return pepsSaved;
		}
		peps.forEach(pep -> pepsSaved.add(save(subsidiary, pep)));
		return pepsSaved;
	}

	@Override
	public List<Pep> findAllByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		List<Pep> peps = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("customer", codeClient); 
			peps = this.repoService.findWithCriteria(subsidiary, Pep.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return peps;
	}

}
