package com.ecommerce.monolith.user.dto;

import com.ecommerce.monolith.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String mobile;
    private Role role;
    private LocalDateTime createdAt;
}
