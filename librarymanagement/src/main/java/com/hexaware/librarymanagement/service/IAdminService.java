package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.AdminDTO;
import java.util.List;

public interface IAdminService {
    // Method to add a new admin
    AdminDTO addAdmin(AdminDTO adminDTO);

    // Method to fetch all admins
    List<AdminDTO> getAllAdmins();

    // Method to fetch a specific admin by ID
    AdminDTO getAdminById(int id);

    // Method to delete an admin by ID
    void deleteAdminById(int id);
}
