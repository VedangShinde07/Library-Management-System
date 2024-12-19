package com.hexaware.librarymanagement.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.librarymanagement.entity.Admin;
import com.hexaware.librarymanagement.entity.User;
import com.hexaware.librarymanagement.repository.AdminRepository;
import com.hexaware.librarymanagement.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public CustomerUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First, try to find the user by username
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            return createUserDetails(user.getUsername(), user.getPassword(), user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet()));
        }

        // Then, try to find the admin by username
        Admin admin = adminRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Invalid username or password."));

        // Return admin details
        return createUserDetails(admin.getUsername(), admin.getPassword(), Set.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    private UserDetails createUserDetails(String username, String password, Set<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                true,  // Account is enabled
                true,  // Account is not expired
                true,  // Credentials are not expired
                true,  // Account is not locked
                authorities
        );
    }
}
