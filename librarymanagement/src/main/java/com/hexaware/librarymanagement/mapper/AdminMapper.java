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
        admin.setUsername(adminDTO.getUsername()); // Use username as the unique identifier for login
        admin.setAdminName(adminDTO.getAdminName()); // Map adminName (may be used for display purposes)
        admin.setEmail(adminDTO.getEmail()); // Map email
        admin.setPassword(adminDTO.getPassword()); // Map password (ensure encrypted storage in real applications)
        admin.setGender(adminDTO.getGender()); // Map gender
        admin.setContactNumber(adminDTO.getContactNumber()); // Map contact number
        admin.setAddress(adminDTO.getAddress()); // Map address
        admin.setProfilePicture(adminDTO.getProfilePicture()); // Map profile picture

        // Map notifications if they are not null
        if (adminDTO.getNotifications() != null) {
            admin.setNotifications(adminDTO.getNotifications().stream()
                    .map(notificationDTO -> mapToAdminNotification(notificationDTO, admin)) // Pass admin reference
                    .collect(Collectors.toList()));
        }

        return admin;
    }

    // Method to map Admin to AdminDTO
    public static AdminDTO mapToAdminDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminId(admin.getAdminId());
        adminDTO.setUsername(admin.getUsername()); // Map username (login identifier)
        adminDTO.setAdminName(admin.getAdminName()); // Map adminName (used for display purposes)
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPassword(admin.getPassword()); // Map password
        adminDTO.setGender(admin.getGender()); // Map gender
        adminDTO.setContactNumber(admin.getContactNumber()); // Map contact number
        adminDTO.setAddress(admin.getAddress()); // Map address
        adminDTO.setProfilePicture(admin.getProfilePicture()); // Map profile picture

        // Map notifications if they are not null
        if (admin.getNotifications() != null) {
            adminDTO.setNotifications(admin.getNotifications().stream()
                    .map(AdminMapper::mapToAdminNotificationDTO) // Map each AdminNotification to AdminNotificationDTO
                    .collect(Collectors.toList()));
        }

        return adminDTO;
    }

    // Helper method to map AdminNotificationDTO to AdminNotification
    private static AdminNotification mapToAdminNotification(AdminNotificationDTO dto, Admin admin) {
        AdminNotification notification = new AdminNotification();
        notification.setNotificationId(dto.getNotificationId());
        notification.setMessage(dto.getMessage());
        notification.setSentDate(dto.getSentDate());
        notification.setAdmin(admin); // Set the admin reference
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
