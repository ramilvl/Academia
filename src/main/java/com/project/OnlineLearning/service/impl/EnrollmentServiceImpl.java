package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Enrollment;
import com.project.OnlineLearning.service.EnrollmentService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveEnrollment(Enrollment enrollment) {
        // Your save logic here, for example:
        entityManager.persist(enrollment);
        // entityManager.flush(); // Uncomment this line if needed
    }
}