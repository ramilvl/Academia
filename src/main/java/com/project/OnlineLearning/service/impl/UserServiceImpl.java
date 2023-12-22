package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Role;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.repository.UserRepository;
import com.project.OnlineLearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public User createStudent(String studentName) {
        User student = new User();
        student.setFirstName(studentName);
        // Set other properties as needed
        return userRepository.save(student);
    }

    @Override
    public User save(User user) {
        User newUser = new User(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getCourses()
        );

        return userRepository.save(newUser);
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

    public Optional<User> getFirstUser() {
        return userRepository.findAll().stream().findFirst();
    }

}
