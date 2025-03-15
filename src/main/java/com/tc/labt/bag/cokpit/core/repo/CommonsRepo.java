package com.tc.labt.sgabs.cokpit.core.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;

public interface CommonsRepo<T> {
	
	public static final Logger log = LoggerFactory.getLogger(CommonsRepo.class);
	
	ErrorOrResult<T> save (String subsidiary, T t);
	List<ErrorOrResult<T>> saveAll(String subsidiary, List<T> tList);

	List<T> findAllByCodeClient(String subsidiary, String codeClient);
}
