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
import com.tc.labt.sgabs.cokpit.core.entity.Leader;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.LeaderRepo;

@Component
public class LeaderRepoImpl implements LeaderRepo{

	@Autowired
	private AbstractCrudRepository<Leader, Long> repoService;

	@Override
	public ErrorOrResult<Leader> save(String subsidiary, Leader leader) {
		if(Objects.isNull(leader))
			return ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT));
		
		ErrorOrResult<Leader> result = null;
		try {
			Leader leaderSaved = this.repoService.saveOrUpdate(subsidiary, leader);
			result = ErrorOrResult.ok(leaderSaved);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
			result = ErrorOrResult.error(e, leader);
		}
		return result;
	}

	@Override
	public List<ErrorOrResult<Leader>> saveAll(String subsidiary, List<Leader> leaders) {
		List<ErrorOrResult<Leader>> leadersSaved = Collections.emptyList();
		if(Objects.isNull(leaders)) {
			leadersSaved.add(ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT)));
			return leadersSaved;
		}
		leaders.forEach(leader-> leadersSaved.add(save(subsidiary, leader)));
		return leadersSaved;
	}

	@Override
	public List<Leader> findAllByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		List<Leader> leaders = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("customer.codeClient", codeClient); 
			leaders = this.repoService.findWithCriteria(subsidiary, Leader.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return leaders;
	}

}
