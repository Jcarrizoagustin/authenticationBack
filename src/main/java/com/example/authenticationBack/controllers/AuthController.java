package com.example.authenticationBack.controllers;

import com.example.authenticationBack.dtos.TokenResponseDTO;
import com.example.authenticationBack.dtos.UserAuthDTO;
import com.example.authenticationBack.services.AuthService;
import com.example.authenticationBack.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody UserAuthDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponseDTO> register(@Valid @RequestBody UserAuthDTO dto){
        TokenResponseDTO responseDTO = authService.register(dto);
        return ResponseEntity.ok(responseDTO);
    }

}
