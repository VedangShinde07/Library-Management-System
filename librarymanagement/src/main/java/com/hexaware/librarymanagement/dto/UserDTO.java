package com.hexaware.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private String profilePicture;
    private boolean status;
}
