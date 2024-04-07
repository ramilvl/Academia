package com.project.OnlineLearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/navigation")
public class NavigationController {
    @GetMapping("/about")
    public String about() {
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
