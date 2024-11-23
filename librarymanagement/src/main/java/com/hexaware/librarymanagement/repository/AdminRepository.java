package com.hexaware.librarymanagement.repository;

import com.hexaware.librarymanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    // You can add custom queries here if needed
}
