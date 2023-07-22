package com.example.authenticationBack.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserCreateDTO {
    private String name;
    private String bio;
    private String phone;
    private String email;
    private String password;
    private MultipartFile image;
}
