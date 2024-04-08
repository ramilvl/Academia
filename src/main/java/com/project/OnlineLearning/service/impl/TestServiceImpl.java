package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Test;
import com.project.OnlineLearning.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl {

    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test findTestById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test with id " + id + " not found"));
    }

    public Test findTestByCourseId(Long courseId) {
        return testRepository.findByCourseId(courseId)
                .orElse(null);
    }

}