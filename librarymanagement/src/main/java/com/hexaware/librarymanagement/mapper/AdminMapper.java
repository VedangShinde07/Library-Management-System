package com.hexaware.librarymanagement.mapper;

import com.hexaware.librarymanagement.dto.AdminDTO;
import com.hexaware.librarymanagement.dto.AdminNotificationDTO;
import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.AdminNotification;

import java.util.stream.Collectors;

public class AdminMapper {

    // Method to map AdminDTO to Admin entity
    public static Admin mapToAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setAdminId(adminDTO.getAdminId());
        admin.setAdminName(adminDTO.getAdminName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());  // Map password

        if (adminDTO.getNotifications() != null) {
            admin.setNotifications(adminDTO.getNotifications().stream()
                    .map(AdminMapper::mapToAdminNotification) // Map each AdminNotificationDTO to AdminNotification
                    .collect(Collectors.toList()));
        }

        return admin;
    }

    // Method to map Admin to AdminDTO
    public static AdminDTO mapToAdminDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminId(admin.getAdminId());
        adminDTO.setAdminName(admin.getAdminName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPassword(admin.getPassword());  // Map password

        if (admin.getNotifications() != null) {
            adminDTO.setNotifications(admin.getNotifications().stream()
                    .map(AdminMapper::mapToAdminNotificationDTO) // Map each AdminNotification to AdminNotificationDTO
                    .collect(Collectors.toList()));
        }

        return adminDTO;
    }

    // Helper method to map AdminNotificationDTO to AdminNotification
    private static AdminNotification mapToAdminNotification(AdminNotificationDTO dto) {
        AdminNotification notification = new AdminNotification();
        notification.setNotificationId(dto.getNotificationId());
        notification.setMessage(dto.getMessage());
        notification.setSentDate(dto.getSentDate());
        return notification;
    }

    // Helper method to map AdminNotification to AdminNotificationDTO
    public static AdminNotificationDTO mapToAdminNotificationDTO(AdminNotification notification) {
        AdminNotificationDTO dto = new AdminNotificationDTO();
        dto.setNotificationId(notification.getNotificationId());
        dto.setMessage(notification.getMessage());
        dto.setSentDate(notification.getSentDate());
        return dto;
    }
}
