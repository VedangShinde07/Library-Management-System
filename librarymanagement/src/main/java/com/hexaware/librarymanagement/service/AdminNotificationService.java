package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.AdminNotification;

import java.util.List;

public interface AdminNotificationService {
    AdminNotification sendNotification(AdminNotification notification);
    List<AdminNotification> getAllNotifications();
    List<AdminNotification> getNotificationsByAdminId(int adminId);
}