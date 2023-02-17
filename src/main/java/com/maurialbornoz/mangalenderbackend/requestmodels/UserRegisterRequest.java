package com.maurialbornoz.mangalenderbackend.requestmodels;

import com.maurialbornoz.mangalenderbackend.annotations.UniqueEmail;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class UserRegisterRequest {
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 40, message = "Password should have more than 8 characters")
    private String password;

    private Boolean locked = false;
    private Boolean enabled = true;


}
