package com.example.authenticationBack.helpers;

import org.springframework.http.MediaType;

public class ManageFormatImage {

    public static MediaType getMediaTypeForImageType(String type){
        return switch (type) {
            case "image/jpeg" -> MediaType.IMAGE_JPEG;
            case "image/png" -> MediaType.IMAGE_PNG;
            default ->
                //TODO Lanzar excepciones personalizadas
                    throw new RuntimeException("Error en el formato de la imagen");
        };
    }
}
