package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.repository.CourseRepository;
import com.project.OnlineLearning.repository.UserRepository;
import com.project.OnlineLearning.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorServiceImpl(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void registerUserForCourse(Long studentId, Long courseId) {
        Optional<User> userOptional = userRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        userOptional.ifPresent(user -> {
            courseOptional.ifPresent(course -> {
                // Add the student to the course
                course.getStudents().add(user);
                courseRepository.save(course);
            });
        });
    }
}
