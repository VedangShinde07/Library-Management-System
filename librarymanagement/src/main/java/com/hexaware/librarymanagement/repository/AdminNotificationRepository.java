package com.hexaware.librarymanagement.repository;

import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.AdminNotification;
import com.hexaware.librarymanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminNotificationRepository extends JpaRepository<AdminNotification, Integer> {
    List<AdminNotification> findByUserUserId(int userId);

    List<AdminNotification> findByAdminAdminId(int adminId);
    // Custom query method to find notifications by admin
    List<AdminNotification> findByAdmin(Admin admin);

    // Custom query method to find notifications by user
    List<AdminNotification> findByUser(User user);
}