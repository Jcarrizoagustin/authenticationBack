package com.example.authenticationBack.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ManageImageImpl implements ManageImage{
    @Override
    public byte[] convertImageToByte(MultipartFile image){
        byte[] bytes = null;
        try{
            bytes = image.getBytes();
        }catch (IOException ex){
            ex.printStackTrace(System.out);
        }
        return bytes;
    }

    @Override
    public MultipartFile convertByteToImage(Byte[] array) {
        // MultipartFile multipartFile = new CustomMultipartFile();
        return null;
    }


}
