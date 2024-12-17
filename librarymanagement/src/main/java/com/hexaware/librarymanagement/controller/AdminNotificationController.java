package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.AdminNotificationDTO;
import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.AdminNotificationMapper;
import com.hexaware.librarymanagement.repository.AdminNotificationRepository;
import com.hexaware.librarymanagement.repository.AdminRepository;
import com.hexaware.librarymanagement.repository.UserRepository;
import com.hexaware.librarymanagement.service.IAdminNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin-notifications")
public class AdminNotificationController {

    @Autowired
    private IAdminNotificationService adminNotificationService;

    @Autowired
    private AdminNotificationRepository adminNotificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/send")
    public ResponseEntity<AdminNotificationDTO> sendNotification(@RequestBody AdminNotificationDTO notificationDTO) {
        if (notificationDTO.getUserId() == 0 || notificationDTO.getAdminId() == 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Data",
                    "User ID and Admin ID are required to send a notification.");
        }

        // Fetch the user and admin from the database
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "User Not Found",
                        "User with ID " + notificationDTO.getUserId() + " does not exist."));

        Admin admin = adminRepository.findById(notificationDTO.getAdminId())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.NOT_FOUND, "Admin Not Found",
                        "Admin with ID " + notificationDTO.getAdminId() + " does not exist."));

        // Map AdminNotificationDTO to AdminNotification entity
        AdminNotification notification = AdminNotificationMapper.mapToAdminNotification(notificationDTO, user, admin);

        // Set the sent date if not already provided
        if (notification.getSentDate() == null) {
            notification.setSentDate(new Date());
        }

        // Save the AdminNotification entity and map it back to AdminNotificationDTO
        AdminNotificationDTO savedNotificationDTO = adminNotificationService.sendNotification(notificationDTO);

        return ResponseEntity.ok(savedNotificationDTO);
    }

    @GetMapping
    public ResponseEntity<List<AdminNotificationDTO>> getAllNotifications() {
        List<AdminNotificationDTO> notifications = adminNotificationService.getAllNotifications();
        if (notifications.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Notifications Found",
                    "There are no notifications available in the system.");
        }
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<AdminNotificationDTO>> getNotificationsByAdminId(@PathVariable int adminId) {
        List<AdminNotificationDTO> notifications = adminNotificationService.getNotificationsByAdminId(adminId);
        if (notifications.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Notifications Found",
                    "No notifications found for admin with ID " + adminId);
        }
        return ResponseEntity.ok(notifications);
    }
}
