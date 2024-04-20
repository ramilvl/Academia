package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.Test;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.repository.CourseRepository;
import com.project.OnlineLearning.repository.TestRepository;
import com.project.OnlineLearning.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    private final TestRepository testRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TestRepository testRepository) {
        this.courseRepository = courseRepository;
        this.testRepository = testRepository;
    }

    @Transactional
    @Override
    public Course createCourse(String courseName, String description) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setDescription(description);
        Course savedCourse = courseRepository.save(course);

        Test defaultTest = new Test();
        defaultTest.setTitle("Default Test for " + savedCourse.getCourseName());
        defaultTest.setCourse(savedCourse);
        testRepository.save(defaultTest);


        return savedCourse;
    }

    @Override
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
