package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.dto.AdminDTO;
import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.mapper.AdminMapper;
import com.hexaware.librarymanagement.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    // Endpoint to add a new admin
    @PostMapping("/add")
    public ResponseEntity<AdminDTO> addAdmin(@RequestBody AdminDTO adminDTO) {
        if (adminDTO == null || adminDTO.getEmail() == null || adminDTO.getAdminName() == null) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Admin Data",
                    "Admin name and email are required.");
        }
        AdminDTO savedAdmin = adminService.addAdmin(adminDTO); // Directly work with AdminDTO
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin); // Return the saved AdminDTO
    }

    // Endpoint to fetch all admins (optional for debugging or viewing admins)
    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        if (admins.isEmpty()) {
            throw new CRUDAPIException(HttpStatus.NOT_FOUND, "No Admins Found",
                    "There are no admins in the system.");
        }
        return ResponseEntity.ok(admins); // Return the list of AdminDTO
    }


    // Endpoint to fetch a specific admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable int id) {
        if (id <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Admin ID",
                    "Admin ID must be a positive number.");
        }
        AdminDTO adminDTO = adminService.getAdminById(id); // Get AdminDTO directly from service
        return ResponseEntity.ok(adminDTO); // Return the AdminDTO
    }

    // Endpoint to delete an admin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable int id) {
        if (id <= 0) {
            throw new CRUDAPIException(HttpStatus.BAD_REQUEST, "Invalid Admin ID",
                    "Admin ID must be a positive number.");
        }
        adminService.deleteAdminById(id);
        return ResponseEntity.ok("Admin deleted successfully.");
    }
}
