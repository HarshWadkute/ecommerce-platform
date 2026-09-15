package com.ecommerce.monolith.user.dto;

import com.ecommerce.monolith.user.User;

public class UserMapper {

    private UserMapper() {

    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .mobile(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
