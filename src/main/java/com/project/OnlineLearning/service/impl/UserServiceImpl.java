package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Role;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.repository.UserRepository;
import com.project.OnlineLearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User save(User user) {
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                List.of(new Role("USER")));

        return userRepository.save(newUser);
    }

    public Optional<User> login(String first_name, String password){
        Optional<User> user = userRepository.findByFirstNameAndPassword(first_name, password);
        return user;
    }
}
