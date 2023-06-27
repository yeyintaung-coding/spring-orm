package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.data.entity.Course;
import com.jdc.data.repo.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/application.xml")
public class CourseRepoTest {

	@Autowired
	private CourseRepo repo;
	
	@ParameterizedTest
	@CsvSource({
		"1,Java Basic,Online Basic,4,30000"
	})
	void test_create(int id,String name,String description,int duration,int fees) {
		var course=new Course(name, description, fees, duration);
		var result=repo.save(course);
		
		assertEquals(id, result.getId());
	}
}
