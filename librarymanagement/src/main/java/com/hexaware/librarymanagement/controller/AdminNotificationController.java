package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.service.AdminNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-notifications")
public class AdminNotificationController {

    @Autowired
    private AdminNotificationService adminNotificationService;

    @PostMapping("/send")
    public ResponseEntity<AdminNotification> sendNotification(@RequestBody AdminNotification notification) {
        return ResponseEntity.ok(adminNotificationService.sendNotification(notification));
    }

    @GetMapping
    public ResponseEntity<List<AdminNotification>> getAllNotifications() {
        return ResponseEntity.ok(adminNotificationService.getAllNotifications());
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<AdminNotification>> getNotificationsByAdminId(@PathVariable int adminId) {
        return ResponseEntity.ok(adminNotificationService.getNotificationsByAdminId(adminId));
    }
}
