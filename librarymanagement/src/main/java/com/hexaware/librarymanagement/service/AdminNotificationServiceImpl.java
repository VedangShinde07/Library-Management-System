package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.repository.AdminNotificationRepository;
import com.hexaware.librarymanagement.service.AdminNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminNotificationServiceImpl implements AdminNotificationService {

    @Autowired
    private AdminNotificationRepository adminNotificationRepository;

    @Override
    public AdminNotification sendNotification(AdminNotification notification) {
        return adminNotificationRepository.save(notification);
    }

    @Override
    public List<AdminNotification> getAllNotifications() {
        return adminNotificationRepository.findAll();
    }

    @Override
    public List<AdminNotification> getNotificationsByAdminId(int adminId) {
        return adminNotificationRepository.findByAdminAdminId(adminId);
    }
}