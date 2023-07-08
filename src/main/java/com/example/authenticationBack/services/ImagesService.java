package com.example.authenticationBack.services;

import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImagesService {
    @Autowired
    private ImageRepository imageRepository;

    private ModelImage convertMultipartFileToModelImage(MultipartFile file){
        ModelImage modelImage = new ModelImage();
        modelImage.setName(file.getName());
        modelImage.setType(file.getContentType());
        try{
            byte[] bytes = file.getBytes();
            modelImage.setBytes(bytes);
        }catch (IOException ex){
            ex.printStackTrace(System.out);
        }
        return modelImage;
    }


    public ModelImage storeModelImage(MultipartFile file){
        ModelImage model = this.convertMultipartFileToModelImage(file);
        return this.imageRepository.save(model);
    }
}
