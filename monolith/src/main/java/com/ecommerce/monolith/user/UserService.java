package com.ecommerce.monolith.user;

import com.ecommerce.monolith.user.dto.RegisterUserRequest;
import com.ecommerce.monolith.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    UserResponse register(RegisterUserRequest registerUserRequest);
    UserResponse getById(Long id);

}
