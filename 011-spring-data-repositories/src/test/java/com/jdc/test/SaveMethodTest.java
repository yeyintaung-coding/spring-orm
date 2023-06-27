package com.jdc.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.JpaConfiguration;
import com.jdc.location.entity.State;
import com.jdc.location.entity.Type;
import com.jdc.location.repo.StateRepo;

@SpringJUnitConfig(classes = JpaConfiguration.class)
public class SaveMethodTest {

	@Autowired
	private StateRepo repo;
	
	@ParameterizedTest
//	@Sql(statements = "truncate table state;")
	@Sql(scripts = "/init-table.sql")
	@CsvFileSource(resources = "/save/test_insert.txt",delimiter = '\t')
	void test_insert(String name,Type type,String region,String capital,int population) {
		//	Prepare Inputs
		var input=new State(name, type, region, capital, population);
		
		//	Execute Test Method
		var result=repo.save(input);
		
		//	Check Result
		assertThat(result, hasProperty("id", is(1)));
	}
}
