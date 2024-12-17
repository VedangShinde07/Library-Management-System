package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.AdminNotificationDTO;
import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.AdminNotificationMapper;
import com.hexaware.librarymanagement.repository.AdminNotificationRepository;
import com.hexaware.librarymanagement.repository.AdminRepository;
import com.hexaware.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminNotificationServiceImpl implements IAdminNotificationService {

    @Autowired
    private AdminNotificationRepository adminNotificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminNotificationDTO sendNotification(AdminNotificationDTO notificationDTO) {
        // Validate and fetch the user
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "User Not Found",
                        "User with ID " + notificationDTO.getUserId() + " does not exist."));

        // Validate and fetch the admin
        Admin admin = adminRepository.findById(notificationDTO.getAdminId())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Admin Not Found",
                        "Admin with ID " + notificationDTO.getAdminId() + " does not exist."));

        // Convert AdminNotificationDTO to AdminNotification entity
        AdminNotification notification = AdminNotificationMapper.mapToAdminNotification(notificationDTO, user, admin);

        // Set the default sent date if not already provided
        if (notification.getSentDate() == null) {
            notification.setSentDate(new Date());
        }

        // Save and map to DTO
        AdminNotification savedNotification = adminNotificationRepository.save(notification);

        // Map the saved AdminNotification entity to AdminNotificationDTO and return
        return AdminNotificationMapper.mapToAdminNotificationDTO(savedNotification);
    }



    @Override
    public List<AdminNotificationDTO> getAllNotifications() {
        List<AdminNotification> notifications = adminNotificationRepository.findAll();
        if (notifications.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Notifications Found",
                    "There are no notifications available in the system.");
        }
        return notifications.stream()
                .map(AdminNotificationMapper::mapToAdminNotificationDTO)  // Map to AdminNotificationDTO
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminNotificationDTO> getNotificationsByAdminId(int adminId) {
        List<AdminNotification> notifications = adminNotificationRepository.findByAdminAdminId(adminId);
        if (notifications.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Notifications Found",
                    "No notifications found for admin with ID " + adminId + ".");
        }
        return notifications.stream()
                .map(AdminNotificationMapper::mapToAdminNotificationDTO)  // Map to AdminNotificationDTO
                .collect(Collectors.toList());
    }
}