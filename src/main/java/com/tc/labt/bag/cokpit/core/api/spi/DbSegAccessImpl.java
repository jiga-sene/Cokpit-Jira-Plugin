package com.tc.labt.sgabs.cokpit.core.api.spi;

import java.sql.Connection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.tc.labt.sgabs.cokpit.commons.MessageError;
import com.tc.labt.sgabs.cokpit.datasource.configuration.ConfigurationService;

@Service
@ExportAsService(DbSegAccess.class)
public class DbSegAccessImpl implements DbSegAccess {	
	
	public static final Logger log = LoggerFactory.getLogger(DbSegAccess.class);

	@Autowired
	private ConfigurationService configurationService;

	@Override
	public Object executeNativeQueryDQL(String subsidiary, String query) throws Exception {
        Session session = this.configurationService.buildSessionFactory(subsidiary).openSession();
        session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery(query);
        Object result = sqlQuery.list();
        session.getTransaction().commit();
        try{
            if(session.isOpen())
                session.close();
        }catch (Exception e){
            log.error(MessageError.TITLE_ERROR + e.getMessage());
        }
        return result;
	}

	@Override
	public Connection getConnection(String subsidiary) throws Exception {
		return this.configurationService.getConnection(subsidiary);
	}

}
