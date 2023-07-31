package com.example.authenticationBack.controllers;

import com.example.authenticationBack.dtos.UserUpdateDTO;
import com.example.authenticationBack.dtos.UserResponseDTO;
import com.example.authenticationBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        UserResponseDTO responseDTO = userService.getUserById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@RequestBody UserUpdateDTO user, @PathVariable Long id){
        UserResponseDTO userResponseDTO = userService.updateUser(user,id);
        return ResponseEntity.ok(userResponseDTO);
    }

}
