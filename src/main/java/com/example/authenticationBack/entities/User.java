package com.example.authenticationBack.entities;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String bio;
    private String phone;
    private String email;
    private String password;
    private ModelImage image;
}
