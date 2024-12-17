package com.hexaware.librarymanagement.mapper;

import com.hexaware.librarymanagement.dto.AdminNotificationDTO;
import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.entity.User;

public class AdminNotificationMapper {

    // Method to map AdminNotificationDTO to AdminNotification entity
    public static AdminNotification mapToAdminNotification(AdminNotificationDTO dto, User user, Admin admin) {
        AdminNotification notification = new AdminNotification();
        notification.setNotificationId(dto.getNotificationId());
        notification.setUser(user); // Set the User entity
        notification.setAdmin(admin); // Set the Admin entity
        notification.setMessage(dto.getMessage());
        notification.setSentDate(dto.getSentDate());
        return notification;
    }

    // Method to map AdminNotification entity to AdminNotificationDTO
    public static AdminNotificationDTO mapToAdminNotificationDTO(AdminNotification notification) {
        AdminNotificationDTO dto = new AdminNotificationDTO();
        dto.setNotificationId(notification.getNotificationId());
        dto.setUserId(notification.getUser().getId()); // Extract and set User ID
        dto.setAdminId(notification.getAdmin().getAdminId()); // Extract and set Admin ID
        dto.setMessage(notification.getMessage());
        dto.setSentDate(notification.getSentDate());
        return dto;
    }
}
