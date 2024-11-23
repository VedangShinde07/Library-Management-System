package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> authenticateUser(String email, String password);
    User getUserById(int userId);
    List<User> getAllUsers();
}