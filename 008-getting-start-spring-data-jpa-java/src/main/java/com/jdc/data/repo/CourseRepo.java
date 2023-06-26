package com.jdc.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.data.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
