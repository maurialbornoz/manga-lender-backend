package com.maurialbornoz.mangalenderbackend.requestmodels;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
public class UserLoginRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
}
