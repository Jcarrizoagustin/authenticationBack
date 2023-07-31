package com.example.authenticationBack.mappers;

import com.example.authenticationBack.dtos.UserUpdateDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelUser;

public class UserMapper {

    public static ModelUser userCreateDTOToModelUser(UserUpdateDTO dto){
        ModelUser user = new ModelUser();
        user.setName(dto.getName());
        user.setBio(dto.getBio());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
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

    public static ModelUser userRegisterDTOToModelUser(UserAuthDTO dto){
        ModelUser modelUser = new ModelUser();
        modelUser.setEmail(dto.getEmail());
        modelUser.setPassword(dto.getPassword());
        return modelUser;
    }
}
