package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.orm.config.AppConfig;
import com.jdc.orm.entity.Course;
import com.jdc.orm.repo.CourseRepo;

@SpringJUnitConfig(classes = AppConfig.class)
public class CourseRepoTest {

	@Autowired
	private CourseRepo repo;
	
	@Test
	void test() {
		var course=new Course("Java Basic","Basic Online Class",4,40000);
		var result=repo.createCourse(course);
		assertEquals(1, result.getId());
	}
}
