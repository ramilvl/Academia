package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Question;
import com.project.OnlineLearning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public int calculateScore(Long testId, Map<String, String> allParams) {
        List<Question> questions = questionRepository.findByTestId(testId);
        int score = 0;

        for (Question question : questions) {
            String submittedAnswer = allParams.getOrDefault("question" + question.getId(), "");
            if (submittedAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }
        }

        return score;
    }
    public List<Question> getQuestionsForTest(Long testId) {
        return questionRepository.findByTestId(testId);
    }
}
