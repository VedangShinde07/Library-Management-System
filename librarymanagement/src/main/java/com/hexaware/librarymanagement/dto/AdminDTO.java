package com.hexaware.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private int adminId;
    private String adminName;
    private String email;
    private String password;
    private List<AdminNotificationDTO> notifications;
}
