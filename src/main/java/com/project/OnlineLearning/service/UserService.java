package com.project.OnlineLearning.service;

import com.project.OnlineLearning.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User save(User user);
    Optional<User> login(String username, String password); 
    Optional<User> getUserById(Long userId); 
    User getUserByUsername(String username);
    void detachUser(User user);
}
