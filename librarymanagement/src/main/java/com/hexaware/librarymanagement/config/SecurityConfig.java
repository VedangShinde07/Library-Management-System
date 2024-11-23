package com.hexaware.librarymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection for now
                .authorizeRequests()
                .requestMatchers("/users/register", "/users/login", "/users/**") // Allow access to registration, login, and all user APIs
                .permitAll()  // Allow all requests without authentication
                .anyRequest().permitAll(); // Permit all other requests as well

        return http.build(); // Return the configured filter chain
    }
}
