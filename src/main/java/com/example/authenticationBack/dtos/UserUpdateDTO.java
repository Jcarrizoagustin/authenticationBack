package com.example.authenticationBack.dtos;


import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String bio;
    private String phone;
    private String email;
    private String password;
}
