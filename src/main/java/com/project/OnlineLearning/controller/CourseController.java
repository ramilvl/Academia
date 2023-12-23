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
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
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
}
