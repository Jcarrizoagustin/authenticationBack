package com.example.authenticationBack.controllers;

import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    private ImagesService imagesService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestBody MultipartFile image){
        ModelImage imageSaved = imagesService.storeModelImage(image);
        return ResponseEntity.ok("Imagen cargada con exito");
    }

}
