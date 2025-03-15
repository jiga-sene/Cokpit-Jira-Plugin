package com.tc.labt.sgabs.cokpit.core.api.spi;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.tc.labt.sgabs.cokpit.datasource.configuration.ConfigurationService;

@Named
@Service
@Scanned
@ExportAsService(SPITest.class)
public class SPITest {

	@Autowired
	ConfigurationService configurationService;
	
	public void createTables() {
		configurationService.buildSessionFactory("SGBG");
	}
}
