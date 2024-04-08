package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



    @GetMapping("/main")
    public String mainPage() {
        return "main-page"; 
    }

    @GetMapping(value = "/admin-dash")
    public String goToAdminDashboard(){
        return "admin-dashboard";
    }

}
