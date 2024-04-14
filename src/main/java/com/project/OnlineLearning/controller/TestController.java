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
        return "test";
    }


    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/{testId}")
    public String submitTest(@PathVariable Long testId, @RequestParam Map<String, String> allParams, Model model) {
        Test test = testService.findTestById(testId);
        List<Question> questions = questionService.getQuestionsForTest(testId);

        Map<Long, String> explanations = new HashMap<>();
        List<Question> incorrectQuestions = new ArrayList<>();

        for (Question question : questions) {
            String userAnswer = allParams.get("question" + question.getId());
            if (!question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
                incorrectQuestions.add(question);
                String explanation = openAIService.getExplanation(question.getContent(), question.getCorrectAnswer());
                explanations.put(question.getId(), explanation);
            }
        }

        int totalCount = questions.size(); // Total number of questions
        int correctCount = totalCount - incorrectQuestions.size(); // Calculate correct count
        int score = totalCount - incorrectQuestions.size(); // Score is the same as correct count

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("score", score);
        model.addAttribute("incorrectQuestions", incorrectQuestions);
        model.addAttribute("explanations", explanations);

        return "result";
    }





}
