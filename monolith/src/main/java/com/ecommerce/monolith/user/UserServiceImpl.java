package com.ecommerce.monolith.user;

import com.ecommerce.monolith.common.exception.DuplicateResourceException;
import com.ecommerce.monolith.common.exception.ResourceNotFoundException;
import com.ecommerce.monolith.user.dto.RegisterUserRequest;
import com.ecommerce.monolith.user.dto.UserMapper;
import com.ecommerce.monolith.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterUserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .mobile(request.getMobile())
                .role(Role.CUSTOMER)
                .build();

        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);

    }

    @Override

    public UserResponse getById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()-> ResourceNotFoundException.create("User",id));
        return UserMapper.toResponse(user);
    }
}
