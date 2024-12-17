package com.hexaware.librarymanagement.mapper;

import com.hexaware.librarymanagement.dto.UserDTO;
import com.hexaware.librarymanagement.entity.Role;
import com.hexaware.librarymanagement.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {

    // Method to map UserDTO to User entity
    public static User mapToUser(UserDTO dto) {
        User user = new User();

        user.setName(dto.getName());
        user.setUsername(dto.getUsername());  // Set username from DTO to entity
        user.setEmail(dto.getEmail());

        // Map the role from DTO (String) to User entity (Set<Role>)
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            Role role = new Role();
            role.setName(dto.getRole()); // Create Role object with the role name
            Set<Role> roles = new HashSet<>();
            roles.add(role);  // Add role to Set
            user.setRoles(roles);  // Assign the Set<Role> to the User entity
        }

        // Additional fields mapping if needed
        user.setGender(dto.getGender());
        user.setContactNumber(dto.getContactNumber());
        user.setAddress(dto.getAddress());
        user.setProfilePicture(dto.getProfilePicture());

        return user;
    }

    // Method to map User entity to UserDTO
    public static UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setName(user.getName());
        dto.setUsername(user.getUsername());  // Include username in the DTO
        dto.setEmail(user.getEmail());

        // Map roles from User entity (Set<Role>) to DTO (String)
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            // Assuming the user has only one role for simplicity
            String roleName = user.getRoles().stream()
                    .findFirst() // Get the first role in the Set
                    .map(Role::getName)  // Extract the role name
                    .orElse(null); // Handle cases where roles may be empty
            dto.setRole(roleName); // Assign the role name to the DTO
        }

        // Additional fields mapping if needed
        dto.setGender(user.getGender());
        dto.setContactNumber(user.getContactNumber());
        dto.setAddress(user.getAddress());
        dto.setProfilePicture(user.getProfilePicture());

        return dto;
    }
}
