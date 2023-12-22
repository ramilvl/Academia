package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.repository.CourseRepository;
import com.project.OnlineLearning.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course createCourse(String courseName) {
        Course course = new Course();
        course.setCourseName(courseName);
        return courseRepository.save(course);
    }

    @Transactional
    public void enrollStudent(Long courseId, User student) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        // Add the student to the course
        course.getStudents().add(student);

        // Update the course and student in the database
        courseRepository.save(course);
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
