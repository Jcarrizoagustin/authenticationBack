package com.example.authenticationBack.dtos;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String bio;
    private String phone;
    private String email;

}
