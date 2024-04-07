package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.enums.Role;
import com.project.OnlineLearning.repository.UserRepository;
import com.project.OnlineLearning.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public Optional<User> login(String username, String password) {
        return userRepository.findByFirstNameAndPassword(username, password);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByFirstName(username);
        return userOptional.orElse(new User());
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachUser(User user) {
        entityManager.detach(user);
    }

    @Override
    public boolean isAdmin(User user) {
        Role userRole = user.getRole();
        return userRole.equals(Role.ADMIN);
    }

    @Override
    public boolean isInstructor(User user) {
        Role userRole = user.getRole();
        return userRole.equals(Role.INSTRUCTOR);
    }
}
