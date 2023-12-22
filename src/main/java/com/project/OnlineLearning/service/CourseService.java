package com.project.OnlineLearning.service;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(String courseName);
    void enrollStudent(Long courseId, User student);
    List<Course> getAllCourses();

    // New method to get course by ID
    Optional<Course> getCourseById(Long courseId);
}