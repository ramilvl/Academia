package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/navigation")
public class NavigationController {

    private final UserService userService;

    @Autowired
    public NavigationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/about")
    public String about(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "about";
    }



    @GetMapping(value = "/main")
    public String mainPage() {
        return "index";
    }

    @GetMapping(value = "/admin-dash")
    public String goToAdminDashboard(){
        return "admin-dashboard";
    }

    @GetMapping(value = "/myCourse")
    public String goToMyCourse(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            Optional<User> userOptional = Optional.ofNullable(userService.getUserByUsername(username));

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                model.addAttribute("courses", user.getCourses());
                model.addAttribute("user", user);

                // Determine the user's role
                String userRole = "STUDENT"; // Default role
                if (userService.isAdmin(user)) {
                    userRole = "ADMIN";
                } else if (userService.isInstructor(user)) {
                    userRole = "INSTRUCTOR";
                }
                model.addAttribute("userRole", userRole); // Add user role to model

                return "my-course";
            }
        }
        model.addAttribute("error", "No user found with the provided username.");
        return "index";
    }

}
