package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.orm.entity.Course;
import com.jdc.orm.repo.CourseRepo;

@SpringJUnitConfig(locations = "classpath:/standalone-orm.xml")
public class StandAloneXmlTest {

	@Autowired
	private CourseRepo repo;
	
	@Test
	void test() {
		var course=new Course("Java Basic", "Basic Online Course", 4, 400000);
		var result=repo.createCourse(course);
		
		assertEquals(1,result.getId());
	}
}
