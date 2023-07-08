package com.example.authenticationBack.helpers;

import org.springframework.http.MediaType;

public class ManageFormatImage {

    public static MediaType getMediaTypeForImageType(String type){
        switch (type){
            case "image/jpeg":
                return MediaType.IMAGE_JPEG;
            case "image/png":
                return MediaType.IMAGE_PNG;
            default:
                //TODO Lanzar excepciones personalizadas
                throw new RuntimeException("Error en el formato de la imagen");
        }
    }
}
