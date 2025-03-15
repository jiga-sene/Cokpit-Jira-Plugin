package com.tc.labt.sgabs.cokpit.datasource.configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Arrays;

import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Component;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.tc.labt.sgabs.cokpit.core.entity.Account;
import com.tc.labt.sgabs.cokpit.core.entity.AdditionalInformation;
import com.tc.labt.sgabs.cokpit.core.entity.Beneficiary;
import com.tc.labt.sgabs.cokpit.core.entity.Customer;
import com.tc.labt.sgabs.cokpit.core.entity.JiraIssue;
import com.tc.labt.sgabs.cokpit.core.entity.Leader;
import com.tc.labt.sgabs.cokpit.core.entity.Pep;
import com.tc.labt.sgabs.cokpit.core.entity.ShareHolder;
import com.tc.labt.sgabs.cokpit.core.entity.ThirdPartyCustomer;
import com.tc.labt.sgabs.cokpit.core.entity.ValidationProcess;

@Named
@Scanned
@Component
public class DatasourceConfigurationServiceImpl implements ConfigurationService{

	@Override
	public SessionFactory buildSessionFactory(String subsidiary) {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		Arrays.asList(Account.class, AdditionalInformation.class, Beneficiary.class, Customer.class, JiraIssue.class, 
				Leader.class, Pep.class, ShareHolder.class, ThirdPartyCustomer.class, ValidationProcess.class)
		.forEach(cfg::addAnnotatedClass);
		
		
		// DEV URL - jdbc:postgresql://182.6.29.87:12400/dbwflx19
		// DEV USER - sgbg
		// DEV PASS - SGBG_pwd7

        cfg.getProperties().setProperty(Environment.URL, "jdbc:postgresql://hg54lx59-g54_af658_dev.fr.world.socgen:12400/hg54lx59");
        cfg.getProperties().setProperty(Environment.USER, "g54fadm");
        cfg.getProperties().setProperty(Environment.PASS, "G54fadm$9");
        cfg.getProperties().setProperty(Environment.DEFAULT_SCHEMA, "public");
        cfg.getProperties().setProperty(Environment.DRIVER, "org.postgresql.Driver");
        cfg.getProperties().setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		
		cfg.getProperties().setProperty(Environment.STATEMENT_BATCH_SIZE, "50");
        cfg.getProperties().setProperty(Environment.HBM2DDL_AUTO, "update");
        cfg.getProperties().setProperty(Environment.AUTO_CLOSE_SESSION, "true");
        cfg.getProperties().setProperty(Environment.POOL_SIZE, "30");
        cfg.getProperties().setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        cfg.getProperties().setProperty(Environment.SHOW_SQL, "false");
        return cfg.buildSessionFactory();
	}

    @Override
    public Connection getConnection(final String subsidiary) throws Exception {

 /*       DatabaseDTO database = this.databaseManager.getByDatasourceName(subsidiary);
        if(database == null) throw MessageError.build(MessageError.DATABASE_NOT_EXIST);
        AbstractDefinition sqlDefinition = SqlDefinitionProvider.build(database.getType().getValue());
        assert sqlDefinition != null;

        String url = sqlDefinition.buildUrlFromConfiguration(database) + "?currentSchema=" + database.getSchema();
        DriverManager.registerDriver((Driver) Class.forName(sqlDefinition.getDriverClassName()).getDeclaredConstructor().newInstance());
        return DriverManager.getConnection(url, database.getLogin(), Encryption.decrypt(database.getSecretKey(), database.getPasswordEncrypted()));
*/
        String url = "jdbc:postgresql://hg54lx59-g54_af658_dev.fr.world.socgen:12400/hg54lx59";
        DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance());
        return DriverManager.getConnection(url, "g54fadm", "G54fadm$9");
    }
}
