package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping
    public String createCourse(@RequestParam String courseName) {
        courseService.createCourse(courseName);
        return "redirect:/courses";
    }

    @GetMapping("/{courseId}")
    public String viewCourse(@PathVariable Long courseId, Model model) {
        Optional<Course> optionalCourse = courseService.getCourseById(courseId);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            model.addAttribute("course", course);
            return "courseDetails";
        } else {
            // Handle the case where the course is not found
            return "courseNotFound"; // Create a Thymeleaf template for displaying a not-found message
        }
    }



}
