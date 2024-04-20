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
@RequestMapping("/instructor")
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
        model.addAttribute("question", question);
        model.addAttribute("courseId", courseId);
        return "add-question";
    }




    @PostMapping("/courses/{id}/addQuestion")
    public String addQuestion(@PathVariable("id") Long courseId, @ModelAttribute("question") Question formQuestion, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add-question";
        }

        Question newQuestion = new Question();
        newQuestion.setContent(formQuestion.getContent());
        newQuestion.setOptionA(formQuestion.getOptionA());
        newQuestion.setOptionB(formQuestion.getOptionB());
        newQuestion.setOptionC(formQuestion.getOptionC());
        newQuestion.setOptionD(formQuestion.getOptionD());
        newQuestion.setCorrectAnswer(formQuestion.getCorrectAnswer());

        Test relatedTest = testService.findTestByCourseId(courseId);
        if (relatedTest == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "No test found for this course. Please create a test first.");
            return "redirect:/instructor/courses/{id}/addQuestion";
        }

        newQuestion.setTest(relatedTest);

        questionRepository.save(newQuestion);

        redirectAttributes.addFlashAttribute("successMessage", "Question added successfully!");
        return "redirect:/instructor/courses/{id}/addQuestion";
    }







}
