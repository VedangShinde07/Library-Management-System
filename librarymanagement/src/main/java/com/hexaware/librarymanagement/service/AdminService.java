package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin addAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(int id);
    void deleteAdminById(int id);
}
