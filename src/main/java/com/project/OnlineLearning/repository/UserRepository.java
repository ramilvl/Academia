package com.project.OnlineLearning.repository;

import com.project.OnlineLearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirstNameAndPassword(String firstName, String password);
    Optional<User> findByFirstName(String firstName); // Add this line
}
