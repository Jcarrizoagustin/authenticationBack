package com.example.authenticationBack.services;

import com.example.authenticationBack.dtos.UserUpdateDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.exceptions.NotFoundException;
import com.example.authenticationBack.mappers.UserMapper;
import com.example.authenticationBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ModelUser storeUser(UserUpdateDTO user){
        ModelUser created = UserMapper.userCreateDTOToModelUser(user);
        return userRepository.save(created);
    }

    public UserResponseDTO getUserById(Long id){
        Optional<ModelUser> userModel = userRepository.findById(id);
        if(userModel.isEmpty()) throw new NotFoundException("El usuario con id" + id.toString() +" no se encuentra registrado");
        return UserMapper.modelUserToUserResponseDTO(userModel.get());
    }

    public ModelUser getModelUserById(Long id){
        Optional<ModelUser> userModel = userRepository.findById(id);
        if(userModel.isEmpty()) throw new NotFoundException("El usuario no se encuentra");
        return userModel.get();
    }

    public ModelUser storeModelUser(ModelUser user){
        //Encrypt password for store it;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserResponseDTO updateUser(UserUpdateDTO dto, Long id){
        ModelUser modelUser = this.updateModelUser(dto,id);
        ModelUser updatedUser = userRepository.save(modelUser);
        return UserMapper.modelUserToUserResponseDTO(updatedUser);
    }

    private ModelUser updateModelUser(UserUpdateDTO dto, Long id){
        ModelUser modelUser = getModelUserById(id);
        if(dto.getName() != null && dto.getName().length() > 0){
            modelUser.setName(dto.getName());
        }
        if(dto.getBio() != null && dto.getBio().length() > 0){
            modelUser.setBio(dto.getBio());
        }
        if(dto.getPhone() != null && dto.getPhone().length() > 0){
            modelUser.setPhone(dto.getPhone());
        }
        if(dto.getEmail() != null && dto.getEmail().length() > 0){
            modelUser.setEmail(dto.getEmail());
        }
        if(dto.getPassword()!=null && dto.getPassword().length() > 0){
            modelUser.setPassword(passwordEncoder.encode(dto.getPassword()));
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
