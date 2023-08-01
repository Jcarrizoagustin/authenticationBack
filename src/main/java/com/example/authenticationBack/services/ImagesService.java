package com.example.authenticationBack.services;

import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.exceptions.BytesErrorException;
import com.example.authenticationBack.exceptions.NotFoundException;
import com.example.authenticationBack.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImagesService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserService userService;

    private ModelImage convertMultipartFileToModelImage(MultipartFile file){
        ModelImage modelImage = new ModelImage();
        modelImage.setName(file.getOriginalFilename());
        modelImage.setType(file.getContentType());
        try{
            byte[] bytes = file.getBytes();
            if(bytes.length == 0){
                throw new BytesErrorException();
            }
            modelImage.setBytes(bytes);
        }catch (IOException ex){
            ex.printStackTrace(System.out);
        }
        return modelImage;
    }


    public void storeModelImage(MultipartFile file, Long id){
        ModelImage model = this.convertMultipartFileToModelImage(file);
        ModelUser user = userService.getModelUserById(id);
        user.addImage(model);
        userService.storeModelUserWithoutEncryptPassword(user);
    }

    public ModelImage getModelImageByUserId(Long id){
        ModelUser model = userService.getModelUserById(id);
        List<ModelImage> modelImageList = imageRepository.findAllByUser(model);
        if(modelImageList.isEmpty()) throw new NotFoundException("No existe imagen de perfil");
        int size = modelImageList.size();
        return modelImageList.get(size - 1);
    }

}
