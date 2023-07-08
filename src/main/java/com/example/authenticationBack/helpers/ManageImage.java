package com.example.authenticationBack.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ManageImage {

    byte[] convertImageToByte(MultipartFile image);

    MultipartFile convertByteToImage(Byte[] array);
}
