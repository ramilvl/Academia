package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.Test;
import com.project.OnlineLearning.service.CourseService;
import com.project.OnlineLearning.service.impl.TestServiceImpl;
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
    private final TestServiceImpl testService;

    @Autowired
    public CourseController(TestServiceImpl testService) {
        this.testService = testService;
    }

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
            model.addAttribute("error", "The requested course was not found.");
            return "errorPage"; // Assuming you have an 'errorPage.html' template
        }
    }


    @GetMapping("/test/start/{courseId}")
    public String startTest(@PathVariable Long courseId, Model model) {
        Test test = testService.findTestByCourseId(courseId);
        if (test != null) {
            model.addAttribute("test", test);
            return "test";
        } else {
            model.addAttribute("error", "Test not found for the given course.");
            return "courseDetails";  // Make sure this template handles the error attribute.
        }
    }

}




