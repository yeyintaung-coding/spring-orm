package com.jdc.location.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.jdc.location.entity")
@EnableJpaRepositories(basePackages = "com.jdc.location.repo",entityManagerFactoryRef = "entityManagerFactoryBean")
public class JpaConfiguration {

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) throws Exception {
		var bean=new LocalContainerEntityManagerFactoryBean();
		
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setJpaProperties(jpaProperties());
		bean.setPackagesToScan("com.jdc.location.entity");
		
		return bean;
	}
	
	private Properties jpaProperties() throws Exception {
		var prop=new Properties();
		prop.load(getClass().getResourceAsStream("/jpa.properties"));
		return prop;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
