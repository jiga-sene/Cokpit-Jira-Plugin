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
import com.tc.labt.sgabs.cokpit.core.entity.Beneficiary;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.BeneficiaryRepo;

@Component
public class BeneficiaryRepoImpl implements BeneficiaryRepo {

	@Autowired
	private AbstractCrudRepository<Beneficiary, Long> repoService;

	@Override
	public ErrorOrResult<Beneficiary> save(String subsidiary, Beneficiary beneficiary) {
		if(Objects.isNull(beneficiary))
			return ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT));
		
		ErrorOrResult<Beneficiary> result = null;
		try {
			Beneficiary beneficiarySaved = this.repoService.saveOrUpdate(subsidiary, beneficiary);
			result = ErrorOrResult.ok(beneficiarySaved);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
			result = ErrorOrResult.error(e, beneficiary);
		}
		return result;
	}

	@Override
	public List<ErrorOrResult<Beneficiary>> saveAll(String subsidiary, List<Beneficiary> beneficiaries) {
		List<ErrorOrResult<Beneficiary>> beneficiariesSaved = Collections.emptyList();
		if(Objects.isNull(beneficiaries)) {
			beneficiariesSaved.add(ErrorOrResult.error(MessageError.build(MessageError.NULL_OBJECT)));
			return beneficiariesSaved;
		}
		beneficiaries.forEach(beneficiary-> beneficiariesSaved.add(save(subsidiary, beneficiary)));
		return beneficiariesSaved;
	}

	@Override
	public List<Beneficiary> findAllByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		List<Beneficiary> beneficiaries = Collections.emptyList();
		try {
			Criterion criterion = Restrictions.eq("customer.codeClient", codeClient); 
			beneficiaries = this.repoService.findWithCriteria(subsidiary, Beneficiary.class, criterion);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return beneficiaries;
	}

}
