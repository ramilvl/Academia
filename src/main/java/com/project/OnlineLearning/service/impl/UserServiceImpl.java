package com.project.OnlineLearning.service.impl;

import com.project.OnlineLearning.entity.Role;
import com.project.OnlineLearning.entity.User;
import com.project.OnlineLearning.repository.RoleRepository;
import com.project.OnlineLearning.repository.UserRepository;
import com.project.OnlineLearning.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;

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
        try {
            Role userRole = user.getRole();

            if (userRole != null && userRole.getId() == null) {
                Role savedRole = roleRepository.save(userRole);
                user.setRole(savedRole);
            }

            // Save the user
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save user: " + e.getMessage());
        }
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
        return userRole != null && userRole.getName().equals("ADMIN");
    }






}
