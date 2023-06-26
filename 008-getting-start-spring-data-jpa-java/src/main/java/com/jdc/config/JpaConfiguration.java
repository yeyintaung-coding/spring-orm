package com.jdc.config;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
//@ComponentScan(basePackages = "com.jdc.data")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jdc.data.repo",entityManagerFactoryRef = "entityManagerFactoryBean")
public class JpaConfiguration {

	private Map<String, ?> jpaProperties(){
		var map=new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto","create");
		map.put("hibernate.show_sql", true);
		map.put("hibernate.format_sql", true);
		return map;
	}

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
		var bean=new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPackagesToScan("com.jdc.data.entity");
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setJpaPropertyMap(jpaProperties());
		return bean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
