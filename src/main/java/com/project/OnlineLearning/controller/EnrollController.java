package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.Enrollment;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.service.CourseService;
import com.project.OnlineLearning.service.EnrollmentService;
import com.project.OnlineLearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/form")
    public String showEnrollmentForm(Model model) {
        List<User> userList = userService.getAllUsers();
        List<Course> courseList = courseService.getAllCourses();

        model.addAttribute("users", userList);
        model.addAttribute("courses", courseList);
        model.addAttribute("enrollment", new Enrollment());

        return "enroll-form";
    }

    @PostMapping("/save")
    public String saveEnrollment(@ModelAttribute("enrollment") Enrollment enrollment, RedirectAttributes redirectAttributes) {
        // Retrieve the user and course objects based on the selected IDs
        Optional<User> optionalUser = userService.getUserById(enrollment.getUser().getId());
        Optional<Course> optionalCourse = courseService.getCourseById(enrollment.getCourse().getId());

        // Check if the objects are present in Optional, otherwise handle the case accordingly
        if (optionalUser.isPresent() && optionalCourse.isPresent()) {
            User selectedUser = optionalUser.get();
            Course selectedCourse = optionalCourse.get();

            // Set the retrieved user and course to the enrollment object
            enrollment.setUser(selectedUser);
            enrollment.setCourse(selectedCourse);

            // Save the enrollment to the database
            enrollmentService.saveEnrollment(enrollment);

            // Add a success message to be displayed on the form
            redirectAttributes.addFlashAttribute("success", "Enrollment successful");

            return "redirect:/enroll/form";
        } else {
            // Handle the case when user or course is not found
            // Add an error message to be displayed on the form
            redirectAttributes.addFlashAttribute("error", "User or course not found");
            return "redirect:/enroll/form";
        }
    }




}