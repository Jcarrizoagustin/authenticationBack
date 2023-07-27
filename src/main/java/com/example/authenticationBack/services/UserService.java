package com.example.authenticationBack.services;

import com.example.authenticationBack.dtos.UserCreateDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.mappers.ModelImageMapper;
import com.example.authenticationBack.mappers.UserMapper;
import com.example.authenticationBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ModelUser storeUser(UserCreateDTO user){
        ModelUser created = UserMapper.userCreateDTOToModelUser(user);
        return userRepository.save(created);
    }

    public UserResponseDTO getUserById(Long id){
        Optional<ModelUser> userModel = userRepository.findById(id);
        if(userModel.isEmpty()) throw new RuntimeException("El usuario no se encuentra");
        return UserMapper.modelUserToUserResponseDTO(userModel.get());
    }

    public ModelUser getModelUserById(Long id){
        Optional<ModelUser> userModel = userRepository.findById(id);
        if(userModel.isEmpty()) throw new RuntimeException("El usuario no se encuentra");
        return userModel.get();
    }

    public ModelUser storeModelUser(ModelUser user){
        //Encrypt password for store it;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserResponseDTO updateUser(UserCreateDTO dto,Long id){
        ModelUser modelUser = this.updateModelUser(dto,id);
        ModelUser updatedUser = userRepository.save(modelUser);
        return UserMapper.modelUserToUserResponseDTO(updatedUser);
    }

    private ModelUser updateModelUser(UserCreateDTO dto,Long id){
        ModelUser modelUser = getModelUserById(id);
        modelUser.setName(dto.getName());
        modelUser.setBio(dto.getBio());
        modelUser.setPhone(dto.getPhone());
        modelUser.setEmail(dto.getEmail());
        if(dto.getPassword()!=null){
            modelUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if(dto.getImage() != null){
            ModelImage image = ModelImageMapper.MultipartFileToModelImage(dto.getImage());
            modelUser.addImage(image);
        }
        return modelUser;
    }

    public boolean verifyUserRegistered(UserAuthDTO dto){
        Optional<ModelUser> modelUser = userRepository.findByEmail(dto.getEmail());
        return modelUser.filter(user -> checkCredentials(dto.getPassword(), user)).isPresent();
    }
    private boolean checkCredentials(String password, ModelUser user){
        return user.getPassword().equals(password);
    }



}
