package com.example.authenticationBack.services;

import com.example.authenticationBack.dtos.TokenResponseDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.mappers.UserMapper;
import com.example.authenticationBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponseDTO login(UserAuthDTO dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));
        ModelUser userDetails = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        //ModelUser modelUser = (ModelUser)userDetails;
        String token = jwtService.getToken(userDetails);
        return new TokenResponseDTO(token,userDetails.getId().toString());
    }



    public TokenResponseDTO register(UserAuthDTO dto){
        ModelUser modelUser = UserMapper.userRegisterDTOToModelUser(dto);
        ModelUser stored = userService.storeModelUser(modelUser);
        String token = jwtService.getToken(modelUser);
        return new TokenResponseDTO(token,stored.getId().toString());
    }

}
