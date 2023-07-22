package com.example.authenticationBack.controllers;

import com.example.authenticationBack.dtos.TokenResponseDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.mappers.UserMapper;
import com.example.authenticationBack.services.AuthService;
import com.example.authenticationBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody UserAuthDTO dto){
        //TODO this method will return a string with an jwt token
        return null;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody UserAuthDTO dto){
        TokenResponseDTO responseDTO = authService.register(dto);
        return ResponseEntity.ok(responseDTO);
    }



    @GetMapping
    public ResponseEntity<String> testing(){
        return ResponseEntity.ok("Path not secured");
    }
}
