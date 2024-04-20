package com.project.OnlineLearning.service;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(String courseName, String description);
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long courseId);

}