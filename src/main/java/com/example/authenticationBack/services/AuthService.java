package com.example.authenticationBack.services;

import com.example.authenticationBack.dtos.TokenResponseDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.mappers.UserMapper;
import com.example.authenticationBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    public TokenResponseDTO login(UserAuthDTO dto){
        return null;
    }

    public TokenResponseDTO register(UserAuthDTO dto){
        ModelUser modelUser = UserMapper.userRegisterDTOToModelUser(dto);
        userService.storeModelUser(modelUser);
        String token = jwtService.getToken(modelUser);
        return new TokenResponseDTO(token);
    }

}
