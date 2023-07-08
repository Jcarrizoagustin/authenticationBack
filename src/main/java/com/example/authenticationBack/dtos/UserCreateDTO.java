package com.example.authenticationBack.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class UserCreateDTO {
    private String name;
    private String bio;
    private String phone;
    private String email;
    private MultipartFile image;
}
