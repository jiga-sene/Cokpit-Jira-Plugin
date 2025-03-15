package com.tc.labt.sgabs.cokpit.core.api.spi;

import java.sql.Connection;

import com.atlassian.annotations.PublicSpi;

@PublicSpi
public interface DbSegAccess {
	
	Object executeNativeQueryDQL(String subsidiary, String sql) throws Exception;

	Connection getConnection(String subsidiary) throws Exception;
}
