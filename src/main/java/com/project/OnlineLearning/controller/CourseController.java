package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.service.CourseService;
import com.project.OnlineLearning.service.impl.CourseServiceImpl;
import com.project.OnlineLearning.service.UserService;  // Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;  // Add this line

    @Autowired
    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;  // Add this line
    }

    // CourseController.java
    @GetMapping
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);

        // For example, let's assume you want to display the enroll form for the first course in the list
        if (!courses.isEmpty()) {
            model.addAttribute("courseId", courses.get(0).getId());
        }

        return "courses";
    }


    @PostMapping
    public String createCourse(@RequestParam String courseName) {
        courseService.createCourse(courseName);
        return "redirect:/courses";
    }

    @PostMapping("/{courseId}/enroll")
    public String enrollStudent(@PathVariable Long courseId, @RequestParam String studentName) {
        User student = userService.createStudent(studentName);  // Use userService here
        courseService.enrollStudent(courseId, student);
        return "redirect:/courses";
    }
}
