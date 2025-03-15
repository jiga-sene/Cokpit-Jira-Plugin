package com.tc.labt.sgabs.cokpit.datasource.configuration;

import java.sql.Connection;

import org.hibernate.SessionFactory;

public interface ConfigurationService {

	SessionFactory buildSessionFactory(final String subsidiary);
	
	Connection getConnection(final String subsidiary) throws Exception;
}
