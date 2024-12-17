package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.JWTAuthResponse;
import com.hexaware.librarymanagement.dto.LoginDTO;
import com.hexaware.librarymanagement.dto.RegisterDTO;

public interface IAuthService {
    JWTAuthResponse authenticateUser(LoginDTO dto);
    String register(RegisterDTO dto);
}
