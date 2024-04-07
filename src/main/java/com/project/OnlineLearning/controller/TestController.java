package com.project.OnlineLearning.controller;

import com.project.OnlineLearning.entity.Question;
import com.project.OnlineLearning.entity.Test;
import com.project.OnlineLearning.service.impl.OpenAIService;
import com.project.OnlineLearning.service.impl.QuestionServiceImpl;
import com.project.OnlineLearning.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tests")
public class TestController {

    private final TestServiceImpl testService;
    private final QuestionServiceImpl questionService;

    @Autowired
    public TestController(TestServiceImpl testService, QuestionServiceImpl questionService) {
        this.testService = testService;
        this.questionService = questionService;
    }

    @GetMapping("/test/{testId}")
    public String showTest(@PathVariable Long testId, Model model) {
        Test test = testService.findTestById(testId);
        List<Question> questions = questionService.getQuestionsForTest(testId);
        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        return "test"; // the name of the Thymeleaf template that shows the questions
    }


    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/{testId}")
    public String submitTest(@PathVariable Long testId, @RequestParam Map<String, String> allParams, Model model) {
        Test test = testService.findTestById(testId);
        List<Question> questions = questionService.getQuestionsForTest(testId);

        // Here you need to calculate the score and determine which answers are incorrect
        int score = questionService.calculateScore(testId, allParams);
        model.addAttribute("score", score);

        // A map to hold the explanations for incorrect answers
        Map<Long, String> explanations = new HashMap<>();
        // A list to hold only the incorrectly answered questions
        List<Question> incorrectQuestions = new ArrayList<>();

        for (Question question : questions) {
            String userAnswer = allParams.get("question" + question.getId());
            if (!question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
                // Add question to the list of incorrect questions
                incorrectQuestions.add(question);
                // Assuming 'getExplanation' returns a String with the explanation or an error message
                String explanation = openAIService.getExplanation(question.getContent(), question.getCorrectAnswer());
                explanations.put(question.getId(), explanation);
            }
        }

        // Debugging: Print explanations to the console
        explanations.forEach((key, value) -> System.out.println("Question ID: " + key + " - Explanation: " + value));

        // Add only incorrect questions and their explanations to the model
        model.addAttribute("incorrectQuestions", incorrectQuestions);
        model.addAttribute("explanations", explanations);

        return "result"; // This is your Thymeleaf template for showing the results
    }



}
