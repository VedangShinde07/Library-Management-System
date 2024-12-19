package com.hexaware.librarymanagement.dto;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserDTO userDto;   // For appending user details and JWT Token in response
    private AdminDTO adminDto; // For appending admin details and JWT Token in response

    // Default constructor
    public JWTAuthResponse() {
    }

    // Constructor for User
    public JWTAuthResponse(String accessToken, UserDTO userDto) {
        this.accessToken = accessToken;
        this.userDto = userDto;
    }

    // Constructor for Admin
    public JWTAuthResponse(String accessToken, AdminDTO adminDto) {
        this.accessToken = accessToken;
        this.adminDto = adminDto;
    }

    // Constructor for both User and Admin (if needed)
    public JWTAuthResponse(String accessToken, UserDTO userDto, AdminDTO adminDto) {
        this.accessToken = accessToken;
        this.userDto = userDto;
        this.adminDto = adminDto;
    }

    // Getters and setters for accessToken and tokenType
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    // Getters and setters for UserDTO
    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    // Getters and setters for AdminDTO
    public AdminDTO getAdminDto() {
        return adminDto;
    }

    public void setAdminDto(AdminDTO adminDto) {
        this.adminDto = adminDto;
    }
}
