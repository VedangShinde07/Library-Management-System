package com.hexaware.librarymanagement.service;

import com.hexaware.librarymanagement.dto.JWTAuthResponse;
import com.hexaware.librarymanagement.dto.LoginDTO;
import com.hexaware.librarymanagement.dto.RegisterDTO;
import com.hexaware.librarymanagement.dto.UserDTO;
import com.hexaware.librarymanagement.entity.Role;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.exception.BadRequestException;
import com.hexaware.librarymanagement.exception.CRUDAPIException;
import com.hexaware.librarymanagement.repository.RoleRepository;
import com.hexaware.librarymanagement.repository.UserRepository;
import com.hexaware.librarymanagement.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService{

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public JWTAuthResponse authenticateUser(LoginDTO dto) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        // Set authentication context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(authentication);

        // Retrieve user details from the database
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new CRUDAPIException(HttpStatus.UNAUTHORIZED, "Authentication Failed",
                        "User not found with the provided username."));

        // Check if password matches the hashed password in the database
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CRUDAPIException(HttpStatus.UNAUTHORIZED, "Invalid credentials", "Password does not match");
        }

        // Map user details to UserDTO
        UserDTO userDto = new UserDTO();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setGender(user.getGender());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setAddress(user.getAddress());
        userDto.setProfilePicture(user.getProfilePicture());

        // Assign the highest role
        String role = user.getRoles().stream()
                .map(Role::getName)
                .sorted()
                .findFirst()
                .orElse("ROLE_USER");
        userDto.setRole(role);

        // Return the JWTAuthResponse
        return new JWTAuthResponse(token, userDto);
    }

    @Override
    @Transactional
    public String register(RegisterDTO dto) {
        // Check if username already exists
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        // Map RegisterDTO to User entity
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // Encode password
        user.setGender(dto.getGender());

        // Set default contact number if not provided
        user.setContactNumber(dto.getContactNumber() != null && !dto.getContactNumber().isEmpty() ? dto.getContactNumber() : "Unknown");

        // Set default address if not provided
        user.setAddress(dto.getAddress() != null && !dto.getAddress().isEmpty() ? dto.getAddress() : "Unknown");

        user.setProfilePicture(dto.getProfilePicture());

        // Handle roles
        Set<Role> roles = new HashSet<>();
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            // Attempt to assign the role provided in the DTO
            Role providedRole = roleRepository.findByName(dto.getRole())
                    .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Invalid role: " + dto.getRole()));
            roles.add(providedRole);
        } else {
            // Assign default role if none provided
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        // Create the default role if it doesn't exist
                        Role newRole = new Role();
                        newRole.setName("ROLE_USER");
                        return roleRepository.save(newRole); // Save the newly created role
                    });
            roles.add(defaultRole);
        }
        user.setRoles(roles);

        // Save the user to the database
        userRepository.save(user);

        return "Registration Successful!";
    }


}
