package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.repository.AdminNotificationRepository;
import com.hexaware.librarymanagement.repository.AdminRepository;
import com.hexaware.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.List;



@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminNotificationRepository adminNotificationRepository;

    @Autowired
    private UserRepository userRepository;  // Repository to fetch users
    // Method to send a notification from an admin to a user

    @Override
    public Admin addAdmin(Admin admin) {
        // Validate admin details if needed
        if (admin.getEmail() == null || admin.getPassword() == null || admin.getAdminName() == null) {
            throw new IllegalArgumentException("Admin name, email, and password cannot be null.");
        }

        // Save the admin to the database
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            throw new IllegalArgumentException("Admin not found with ID: " + id);
        }
        return admin.get();
    }

    @Override
    public void deleteAdminById(int id) {
        if (!adminRepository.existsById(id)) {
            throw new IllegalArgumentException("Admin not found with ID: " + id);
        }
        adminRepository.deleteById(id);
    }
    public void sendNotificationToUser(int adminId, int userId, String message) {
        // Fetch the admin by ID
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            throw new IllegalArgumentException("Admin with id " + adminId + " not found");
        }
        Admin admin = adminOptional.get();

        // Fetch the user by ID
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }
        User user = userOptional.get();

        // Create a new notification
        AdminNotification notification = new AdminNotification();
        notification.setAdmin(admin);  // Set the admin who is sending the notification
        notification.setUser(user);    // Set the user who will receive the notification
        notification.setMessage(message); // Set the notification message
        notification.setSentDate(new Date()); // Set the current timestamp as the sent date

        // Save the notification to the database
        adminNotificationRepository.save(notification);
    }

    // Method to get all notifications sent by a specific admin
    public List<AdminNotification> getNotificationsByAdmin(int adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            throw new IllegalArgumentException("Admin with id " + adminId + " not found");
        }
        Admin admin = adminOptional.get();
        
        return adminNotificationRepository.findByAdmin(admin);
    }

    // Method to get all notifications received by a specific user
    public List<AdminNotification> getNotificationsByUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }
        User user = userOptional.get();
        
        return adminNotificationRepository.findByUser(user);
    }

    // Method to delete a notification
    public void deleteNotification(int notificationId) {
        Optional<AdminNotification> notificationOptional = adminNotificationRepository.findById(notificationId);
        if (notificationOptional.isEmpty()) {
            throw new IllegalArgumentException("Notification with id " + notificationId + " not found");
        }

        adminNotificationRepository.delete(notificationOptional.get());
    }

    // Method to get details of a specific notification
    public AdminNotification getNotificationDetails(int notificationId) {
        Optional<AdminNotification> notificationOptional = adminNotificationRepository.findById(notificationId);
        if (notificationOptional.isEmpty()) {
            throw new IllegalArgumentException("Notification with id " + notificationId + " not found");
        }

        return notificationOptional.get();
    }
}
