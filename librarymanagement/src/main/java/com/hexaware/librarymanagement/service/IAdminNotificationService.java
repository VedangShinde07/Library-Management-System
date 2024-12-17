package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.AdminNotificationDTO;
import com.hexaware.librarymanagement.entity.AdminNotification;

import java.util.List;

public interface IAdminNotificationService {
    AdminNotificationDTO sendNotification(AdminNotificationDTO notificationDTO);
    List<AdminNotificationDTO> getAllNotifications();
    List<AdminNotificationDTO> getNotificationsByAdminId(int adminId);
}
