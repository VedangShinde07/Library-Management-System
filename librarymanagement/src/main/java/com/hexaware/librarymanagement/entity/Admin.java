package com.hexaware.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;  // Primary key for the admin entity

    private String adminName;
    private String email;
    private String password;  // In real systems, avoid storing passwords as plain text. Use proper encryption methods.

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdminNotification> notifications;  // List of notifications sent by this admin
}
