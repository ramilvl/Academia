package com.project.OnlineLearning.controller;
import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.service.InstructorService;
import com.project.OnlineLearning.service.CourseService;
import com.project.OnlineLearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService, UserService userService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", userService.getAllUsers());
        return "instructor/register";
    }

    @PostMapping("/register")
    public String registerStudentsForCourse(@RequestParam Long courseId, @RequestParam Long studentId) {
        instructorService.registerUserForCourse(studentId, courseId);
        System.out.println("Registration successful!"); // Add logging
        return "redirect:/instructor/register?success";
    }


}
