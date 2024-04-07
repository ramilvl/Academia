package com.project.OnlineLearning.repository;

import com.project.OnlineLearning.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByCourseId(Long courseId);
}