package com.tc.labt.sgabs.cokpit.core.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public abstract class AbstractCrudRepository<T, ID> {

	public abstract T saveOrUpdate(String subsidiary, T t) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	public abstract T findById(String subsidiary, ID id, Class aClass) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	public abstract List<T> findAll(String subsidiary, int maxResults, Class aClass) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	abstract List<T> findAll(String subsidiary, int page, int size, Class aClass) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	abstract int getCount(String subsidiary, Class aClass, Criterion criterion) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	public abstract List<T> findWithCriteria(String subsidiary, int page, int size, Class aClass,
			Criterion... criterions) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	public abstract List<T> findWithCriteriaAndOrder(String subsidiary, int page, int size, Class aClass,
			Criterion criterion, Order... orders) throws Exception;

	@SuppressWarnings(value = {"rawtypes"})
	public abstract List<T> findWithCriteria(String subsidiary, Class aClass, Criterion... criterions) throws Exception;

	public abstract SessionFactory getSessionFactory(String subsidiary) throws Exception;

	public void closeSession(Session session) {
		try {
			session.close();
		} catch (Exception e) {
			/* No necessary to log error */}
	}
}
