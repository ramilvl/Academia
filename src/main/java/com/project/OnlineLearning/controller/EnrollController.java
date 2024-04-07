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
        Optional<User> optionalUser = userService.getUserById(enrollment.getUser().getId());
        Optional<Course> optionalCourse = courseService.getCourseById(enrollment.getCourse().getId());

        if (optionalUser.isPresent() && optionalCourse.isPresent()) {
            User selectedUser = optionalUser.get();
            Course selectedCourse = optionalCourse.get();

            enrollment.setUser(selectedUser);
            enrollment.setCourse(selectedCourse);

            enrollmentService.saveEnrollment(enrollment);

            userService.detachUser(selectedUser);

            redirectAttributes.addFlashAttribute("success", "Enrollment successful");

            return "redirect:/enroll/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "User or course not found");
            return "redirect:/enroll/form";
        }
    }

}