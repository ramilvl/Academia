package com.project.OnlineLearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses/{courseId}")
public class TestingController {

    @GetMapping("/lectures")
    public String viewLectures(@PathVariable Long courseId, Model model) {
        // Logic to display lectures for the course
        return "lecturePage"; // Thymeleaf template to display lectures
    }

    @GetMapping("/test")
    public String startTest(@PathVariable Long courseId, Model model) {
        // Logic to start testing for the course
        return "testPage"; // Thymeleaf template to display testing interface
    }
}
