package com.hexaware.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminNotificationDTO {
    private int notificationId;
    private int userId;  // The ID of the user related to this notification
    private int adminId; // The ID of the admin who created the notification
    private String message;
    private Date sentDate;
}
