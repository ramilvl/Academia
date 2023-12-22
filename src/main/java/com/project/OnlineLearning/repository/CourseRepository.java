package com.project.OnlineLearning.repository;

import com.project.OnlineLearning.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
