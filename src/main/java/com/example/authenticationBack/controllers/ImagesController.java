package com.example.authenticationBack.controllers;

import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.helpers.ManageFormatImage;
import com.example.authenticationBack.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id){
        ModelImage image = imagesService.getModelImageById(id);
        byte[] result = image.getBytes();

        HttpHeaders headers = new HttpHeaders();
        MediaType media = ManageFormatImage.getMediaTypeForImageType(image.getType());
        headers.setContentType(media);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }

}
