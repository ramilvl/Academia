package com.project.OnlineLearning.service;

import com.project.OnlineLearning.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User createStudent(String studentName);
    User save(User user);
    Optional<User> login(String username, String password);
    Optional<User> getUserById(Long userId);
    User getUserByUsername(String username); // New method to get user by username
}
