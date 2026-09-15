package com.ecommerce.monolith.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    @NotBlank(message="Email is required")
    @Email(message="Email must be valid")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=8,message="Password should be at least 8 characters")
    private String password;


    @NotBlank(message="Name is required")
    @Size(min=2, message="Full name must be between 2 and 100 characters" )
    private String fullName;

    @Pattern(regexp = "[0-9]{10}$", message="Mobile must be exactly 10 digits")
    private String mobile;

}
