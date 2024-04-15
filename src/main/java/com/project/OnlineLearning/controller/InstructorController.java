package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Course;
import com.project.OnlineLearning.entity.Question;
import com.project.OnlineLearning.entity.Test;
import com.project.OnlineLearning.repository.QuestionRepository;
import com.project.OnlineLearning.service.impl.CourseServiceImpl;
import com.project.OnlineLearning.service.impl.TestServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/instructor") // Base URL for all handlers in this controller
public class InstructorController {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TestServiceImpl testService;

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/courses/{id}/addQuestion")
    public String showAddQuestionForm(@PathVariable("id") Long courseId, Model model) {
        Question question = new Question();
        // You may need to set other properties or fetch related entities if necessary
        model.addAttribute("question", question);
        model.addAttribute("courseId", courseId);
        return "add-question";
    }




    @PostMapping("/courses/{id}/addQuestion")
    public String addQuestion(@PathVariable("id") Long courseId, @ModelAttribute("question") Question question, RedirectAttributes redirectAttributes) {
        // Additional logic for setting the course or test related to the question
        // For example: question.setTest(testService.findTestById(courseId));
        questionRepository.save(question);
        redirectAttributes.addFlashAttribute("successMessage", "Question added successfully!");
        return "redirect:/instructor/courses/{id}/addQuestion";
    }





}
