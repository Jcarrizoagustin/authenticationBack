package com.example.authenticationBack.services;

import com.example.authenticationBack.dtos.UserCreateDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.mappers.UserMapper;
import com.example.authenticationBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ModelUser storeUser(UserCreateDTO user){
        ModelUser created = UserMapper.userCreateDTOToModelUser(user);
        return userRepository.save(created);
    }

    public UserResponseDTO getUserById(Long id){
        Optional<ModelUser> userModel = userRepository.findById(id);
        if(userModel.isEmpty()) throw new RuntimeException("El usuarion no se encuentra");
        return UserMapper.modelUserToUserResponseDTO(userModel.get());
    }

    public ModelUser getModelUserById(Long id){
        Optional<ModelUser> userModel = userRepository.findById(id);
        if(userModel.isEmpty()) throw new RuntimeException("El usuarion no se encuentra");
        return userModel.get();
    }
}
