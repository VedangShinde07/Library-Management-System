package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.AdminDTO;
import com.hexaware.librarymanagement.dto.JWTAuthResponse;
import com.hexaware.librarymanagement.dto.LoginDTO;
import com.hexaware.librarymanagement.dto.RegisterDTO;
import com.hexaware.librarymanagement.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@CrossOrigin("*")
public class AuthController {

    private IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO dto) {
        try {
            // Authenticate the user or admin
            JWTAuthResponse jwtAuthResponse = authService.authenticateUser(dto);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (Exception e) {
            // Return UNAUTHORIZED if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO dto) {
        // Call the service to register the user
        String message = authService.register(dto);
        // Return a response with CREATED status
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/registerAdmin"})
    public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO adminDTO) {
        // Call the service to register the admin
        String message = authService.registerAdmin(adminDTO);
        // Return a response with CREATED status
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
