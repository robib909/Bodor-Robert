package com.example.library_management.service;

import com.example.library_management.User;
import com.example.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Returns null if user not found
    }

    // Save a new user
    public User saveUser(User user) {
        return userRepository.save(user); // Saves user to the database
    }

    // Delete a user by id
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Deletes user by id
    }
}
