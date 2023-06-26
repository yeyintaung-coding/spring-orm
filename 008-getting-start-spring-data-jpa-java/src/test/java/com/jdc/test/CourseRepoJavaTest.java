package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.config.JpaConfiguration;
import com.jdc.data.entity.Course;
import com.jdc.data.repo.CourseRepo;


@SpringJUnitConfig(classes = JpaConfiguration.class)
public class CourseRepoJavaTest {

	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"Java Basic,4,30000,1"
	})
	void test_create(String name,int duration,int fees,int id) {
		var course=new Course(name, duration, fees);
		var result=repo.save(course);
		
		assertEquals(id, result.getId());
	}
}
