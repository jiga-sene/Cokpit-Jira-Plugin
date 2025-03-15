package com.tc.labt.sgabs.cokpit.core.repo.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.tc.labt.sgabs.cokpit.commons.ErrorOrResult;
import com.tc.labt.sgabs.cokpit.commons.MessageError;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPaginationDTO;
import com.tc.labt.sgabs.cokpit.core.dto.CustomerPaginationHandler;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.core.repo.AbstractCrudRepository;
import com.tc.labt.sgabs.cokpit.core.repo.CustomerRepo;
import com.tc.labt.sgabs.cokpit.datatable.Pagination;

@Component
public class CustomerRepoImpl implements CustomerRepo{

	
	@Autowired
	private AbstractCrudRepository<Customer, String> repoService;
	
	@Override
	public ErrorOrResult<Customer> saveCustomer(String subsidiary, Customer customer) {
		if(Objects.isNull(customer))
			return ErrorOrResult.error(MessageError.build(MessageError.TITLE_ERROR + MessageError.NULL_OBJECT));
		
		ErrorOrResult<Customer> result = null;
		try {
			Customer customerSaved = this.repoService.saveOrUpdate(subsidiary, customer);
			result = ErrorOrResult.ok(customerSaved);
		} catch (Exception e) {
			result = ErrorOrResult.error(e, customer);
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return result;
	}

	@Override
	public Customer findCustomerByCodeClient(String subsidiary, String codeClient) {
		if(Strings.isNullOrEmpty(codeClient))
			return null;
		
		Customer customer = null;
		try {
			customer = this.repoService.findById(subsidiary, codeClient, Customer.class);
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}
		return customer;
	}
	
	@Override
	public Pagination<CustomerPaginationDTO> retrieveDataPagination(String subsidiary, int page, int size, Criterion criterion){
		Pagination<CustomerPaginationDTO> customersPagination = new Pagination<CustomerPaginationDTO>();
		Session session = null;
		try {
			session = this.repoService.getSessionFactory(subsidiary).openSession();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(criterion);
			/*criteria.setProjection(Projections.projectionList()
					.add(Projections.property("codeClient"))
					.add(Projections.property("prenomClient"))
					.add(Projections.property("nomClient"))
					.add(Projections.property("raisonSociale"))
					.add(Projections.property("displayName"))
					.add(Projections.property("referenceTypeClient"))
					.add(Projections.property("libelleTypeClient"))
					.add(Projections.property("statutKYC"))
					.add(Projections.property("segmentClient"))
					.add(Projections.property("avisRCOFatca"))
					.add(Projections.property("classificationRisque"))
					.add(Projections.property("flagPEP")));*/
			customersPagination.setRecordsTotal(criteria.list().size());
			if(size>0)
				criteria.setFirstResult(--page * size).setMaxResults(size);
			
			@SuppressWarnings("unchecked")
			List<CustomerPaginationDTO> customersPaginationDTO = CustomerPaginationHandler.retrieveCustomersPagination((List<Customer>) criteria.list());
			
			customersPagination.setData(customersPaginationDTO);
			customersPagination.setRecordsFiltered(customersPagination.getData().size());
			
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}finally {
			repoService.closeSession(session);
		}
		return customersPagination;
	}
	
	public List<Customer> retrieveDataTEST(String subsidiary){
		Session session = null;

		List<Customer> customersPaginationDTO = null;
		try {
			session = this.repoService.getSessionFactory(subsidiary).openSession();
			Criteria criteria = session.createCriteria(Customer.class);

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("codeClient").as("codeClient"))
					/*.add(Projections.property("prenomClient"))
					.add(Projections.property("nomClient"))
					.add(Projections.property("raisonSociale"))*/
					.add(Projections.property("displayName").as("displayName"))
//					.add(Projections.property("referenceTypeClient").as("referenceTypeClient"))
					.add(Projections.property("libelleTypeClient").as("libelleTypeClient"))
					.add(Projections.property("statutKYC").as("statutKYC"))
					.add(Projections.property("segmentClient").as("segmentClient"))
					.add(Projections.property("classificationRisque").as("classificationRisque"))
					.add(Projections.property("avisRCOFatca").as("avisRCOFatca"))
					.add(Projections.property("flagPEP").as("flagPEP")));
			criteria.setFirstResult(0).setMaxResults(10);
			
			
			customersPaginationDTO = (List<Customer>)criteria.list(); //CustomerPaginationHandler.retrieveCustomersPagination((List<Customer>) criteria.setResultTransformer(Transformers.aliasToBean(Customer.class)).list()); 	
			
		} catch (Exception e) {
			log.error(MessageError.TITLE_ERROR + e.getMessage());
		}finally {
			repoService.closeSession(session);
		}
		return customersPaginationDTO;
	}

}
