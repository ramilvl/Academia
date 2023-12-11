package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    
    @GetMapping("/")
    public String loginPage(){
        return "index";
    }

    @PostMapping("/login")
    public String loginToWebsite(@RequestParam String username, @RequestParam String password, Model model){
       Optional<User> user =  userService.login(username, password);
       if(!user.isPresent()){
           model.addAttribute("error", "There is no such user!");
           return "login";
       }
       return "main-page";
    }
    
    @GetMapping("/register")
    public String registerUser(){
        return "register";
    }
    
    @PostMapping("/register")
    public String registerToWebsite(User user){
        userService.save(user);
        return "index"; 
    }
}
