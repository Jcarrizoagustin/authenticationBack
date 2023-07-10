package com.example.authenticationBack.controllers;

import com.example.authenticationBack.dtos.UserCreateDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.entities.ModelUser;
import com.example.authenticationBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> storeUser(@RequestBody @ModelAttribute UserCreateDTO user){
        ModelUser stored = userService.storeUser(user);
        return ResponseEntity.ok("Usuario almacenado con exito");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        UserResponseDTO responseDTO = userService.getUserById(id);
        return ResponseEntity.ok(responseDTO);
    }


}
