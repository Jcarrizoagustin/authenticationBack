package com.example.authenticationBack.mappers;

import com.example.authenticationBack.entities.ModelImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ModelImageMapper {

    public static ModelImage MultipartFileToModelImage(MultipartFile file){
        ModelImage model = new ModelImage();
        model.setName(file.getOriginalFilename());
        model.setType(file.getContentType());
        try{
            model.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return model;
    }
}
