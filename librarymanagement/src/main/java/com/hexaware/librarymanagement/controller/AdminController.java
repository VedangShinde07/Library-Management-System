package com.hexaware.librarymanagement.controller;

import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint to add a new admin
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.addAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // Endpoint to fetch all admins (optional for debugging or viewing admins)
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    // Endpoint to fetch a specific admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    // Endpoint to delete an admin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable int id) {
        adminService.deleteAdminById(id);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

}
