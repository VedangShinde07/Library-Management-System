package com.hexaware.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "admin_notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne  // Assuming an Admin entity exists
    @JoinColumn(name = "admin_id", nullable = false)  // Foreign key to Admin entity
    private Admin admin;  // Link to the admin who sent the notification

    private String message;

    @Column(nullable = false)
    private Date sentDate;
}