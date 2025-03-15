package com.tc.labt.sgabs.cokpit.core.repo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.commons.MessageError;
import com.tc.labt.sgabs.cokpit.datasource.configuration.ConfigurationService;

@Component
public class DatabaseRepository<T, ID extends Serializable> extends AbstractCrudRepository<T, ID>{
	
	@Autowired
	private ConfigurationService configurationService;
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseRepository.class);
	public static final int PAGE_SIZE = 10000;

	@Override
	public T saveOrUpdate(String subsidiary, T t) throws Exception {
        if(Objects.isNull(t))
            return null;
        SessionFactory sessionFactory = getSessionFactory(subsidiary);
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            tx.commit();
        }catch (Exception e){
            log.error(e.getMessage());
            tx.rollback();
            throw e;
        }finally {
            closeSession(session);
        }
        return t;
	}

	@Override
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public T findById(String subsidiary, ID id, Class aClass) throws Exception {
        if(Objects.isNull(id))
            return null;
        Session session = null;
        T result;
        try {
            session = this.getSessionFactory(subsidiary).openSession();
            result = (T) session.get(aClass, id);
        }catch (Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
            throw e;
        }finally {
            closeSession(session);
        }
        return result;
	}

	@Override
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public List<T> findAll(String subsidiary, int maxResults, Class aClass) throws Exception {
        Session session = this.getSessionFactory(subsidiary).openSession();
        List<T> results;
        try {
            Criteria criteria = session.createCriteria(aClass);
            results = criteria.setMaxResults(maxResults < 0 ? PAGE_SIZE : maxResults).list();
        }catch (Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
            throw e;
        }finally {
            closeSession(session);
        }
        return results;
	}

	@Override
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	List<T> findAll(String subsidiary, int page, int size, Class aClass) throws Exception {
        Session session = this.getSessionFactory(subsidiary).openSession();
        List<T> results;
        try {
            results = session.createCriteria(aClass).setFirstResult((--page<0? 0 : page) * size).setMaxResults(size>0 && size<PAGE_SIZE? size : PAGE_SIZE).list();
        }catch (Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
            throw e;
        }finally {
            closeSession(session);
        }
        return results;
	}

	@Override
	@SuppressWarnings(value = {"rawtypes"})
	int getCount(String subsidiary, Class aClass, Criterion criterion) throws Exception {
        Session session = this.getSessionFactory(subsidiary).openSession();
        int count;
        try {
            Criteria criteria = session.createCriteria(aClass);
            if(Objects.nonNull(criterion))
                criteria.add(criterion);
            count = criteria.list().size();
            closeSession(session);
        }catch (Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
            throw e;
        }finally {
            closeSession(session);
        }
        return count;
	}

	@Override
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public List<T> findWithCriteria(String subsidiary, int page, int size, Class aClass, Criterion... criterions)
			throws Exception {
        Session session = this.getSessionFactory(subsidiary).openSession();
        List<T> results;
        try {
            Criteria criteria = session.createCriteria(aClass);
            Arrays.asList(criterions).forEach(criteria::add);
            if(size > 0)
                criteria.setFirstResult(--page * size).setMaxResults(size);
            results = criteria.list();
        }catch (Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
            throw e;
        }finally {
            closeSession(session);
        }
        return results;
	}

	@Override
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public List<T> findWithCriteriaAndOrder(String subsidiary, int page, int size, Class aClass, Criterion criterion,
			Order... orders) throws Exception {
        Session session = this.getSessionFactory(subsidiary).openSession();
        List<T> results;
        try {
            Criteria criteria = session.createCriteria(aClass);
            if (size > 0)
                criteria.setFirstResult(--page * size).setMaxResults(size);
            criteria.add(criterion);
            Arrays.asList(orders).forEach(criteria::addOrder);
            results = criteria.list();
        }catch(Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
            throw e;
        }finally {
            closeSession(session);
        }
        return results;
	}

	@Override
	@SuppressWarnings(value = {"rawtypes"})
	public List<T> findWithCriteria(String subsidiary, Class aClass, Criterion... criterions) throws Exception {
        return findWithCriteria(subsidiary, 1, -1, aClass, criterions);
	}

    @Override
    public SessionFactory getSessionFactory(String subsidiary) throws Exception{
        if (Strings.isNullOrEmpty(subsidiary))
            throw MessageError.build("The entity is required.");
        SessionFactory sessionFactory = this.configurationService.buildSessionFactory(subsidiary); //getSessionFactory(subsidiary);
        if(Objects.nonNull(sessionFactory))
            return sessionFactory;
        throw MessageError.build("The datasource of entity {"+subsidiary+"} not configured.");
    }
}
