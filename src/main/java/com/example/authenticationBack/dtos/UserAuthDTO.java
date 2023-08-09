package com.example.authenticationBack.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthDTO {
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
