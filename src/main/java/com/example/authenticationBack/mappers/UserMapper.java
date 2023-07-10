package com.example.authenticationBack.mappers;

import com.example.authenticationBack.dtos.UserCreateDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.entities.ModelUser;

public class UserMapper {

    public static ModelUser userCreateDTOToModelUser(UserCreateDTO dto){
        ModelUser user = new ModelUser();
        user.setName(dto.getName());
        user.setBio(dto.getBio());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        ModelImage image = ModelImageMapper.MultipartFileToModelImage(dto.getImage());
        user.addImage(image);
        return user;
    }

    public static UserResponseDTO modelUserToUserResponseDTO(ModelUser modelUser){
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(modelUser.getId());
        responseDTO.setName(modelUser.getName());
        responseDTO.setPhone(modelUser.getPhone());
        responseDTO.setEmail(modelUser.getEmail());
        responseDTO.setBio(modelUser.getBio());
        return responseDTO;
    }
}
