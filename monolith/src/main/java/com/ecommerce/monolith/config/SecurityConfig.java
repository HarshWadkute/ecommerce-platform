package com.ecommerce.monolith.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())  // what do you think — enable or disable, for a stateless REST API?
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/v1/users/register", "/api/v1/users/login").permitAll()
                    .anyRequest().permitAll()  // authenticated? permitAll? which, and why?
        );
       return http.build();
    }
}
