package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.UserDTO;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        // Fetch user details using the service and map to UserDTO
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO == null) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "User Not Found",
                    "User with ID " + userId + " does not exist.");
        }
        return ResponseEntity.ok(userDTO); // Return the user details as UserDTO
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // Fetch all users and return as a list of UserDTOs
        List<UserDTO> userDTOs = userService.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }
}