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

    @Column(nullable = false, unique = true)
    private String adminName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;  // In real systems, avoid storing passwords as plain text. Use proper encryption methods.

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String contactNumber;

    @Column(nullable = false)
    private String address;
    private String profilePicture;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdminNotification> notifications;  // List of notifications sent by this admin
}
