    package com.project.OnlineLearning.controller;
    
    import com.project.OnlineLearning.entity.User;
    import com.project.OnlineLearning.service.CourseService;
    import com.project.OnlineLearning.service.impl.UserServiceImpl;
    import jakarta.servlet.http.HttpServletRequest;
    import lombok.AllArgsConstructor;
    import lombok.NoArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    
    import java.security.Principal;
    import java.util.Optional;
    
    @Controller
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserController {
        @Autowired
        private UserServiceImpl userService;
    
        @Autowired
        private CourseService courseService;
    
        @GetMapping("/")
        public String loginPage() {
            return "index";
        }
    
        @GetMapping("/main-page")
        public String mainPage(Model model, HttpServletRequest request) {
            String username = (String) request.getSession().getAttribute("username");
    
            if (username != null) {
                Optional<User> userOptional = Optional.ofNullable(userService.getUserByUsername(username));
    
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    model.addAttribute("courses", user.getCourses());
                    model.addAttribute("user", user);
                    return "main-page";
                }
            }
    
            model.addAttribute("error", "No user found with the provided username.");
            return "index";
        }
    
    
    
    
    
        @PostMapping("/login")
        public String loginToWebsite(@RequestParam String first_name, @RequestParam String password, Model model, HttpServletRequest request) {
            Optional<User> user = userService.login(first_name, password);
            if (!user.isPresent()) {
                model.addAttribute("error", "There is no such user!");
                return "index";
            }
    
            // Set the username in the session attribute
            request.getSession().setAttribute("username", first_name);
    
            model.addAttribute("user", user.get());
            return "redirect:/main-page";
        }
    
    
        @GetMapping("/register")
        public String registerUser(Model model) {
            model.addAttribute("user", new User());
            return "register";
        }
    
        @PostMapping("/save")
        public String registerToWebsite(@ModelAttribute User user) {
            userService.save(user);
            return "redirect:/";
        }
    }
