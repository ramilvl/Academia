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
        // Implement the logic to retrieve a test based on the course ID.
        // This could involve querying your repository based on the courseId,
        // which you might need to add to your Test entity as a foreign key if it's not there already.
        // Here's a hypothetical example:
        return testRepository.findByCourseId(courseId)
                .orElse(null); // Use Optional to handle the case where the test isn't found
    }

}