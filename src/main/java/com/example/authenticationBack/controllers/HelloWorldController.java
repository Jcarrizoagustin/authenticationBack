package com.example.authenticationBack.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hola Mundo");
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getNames(){
        List<String> names = new ArrayList<>();
        names.add("Armani");
        names.add("Caso");
        names.add("Gonzáles Pirez");
        names.add("Paulo Diaz");
        names.add("Enzo Diaz");
        names.add("Enzo Pérez");
        names.add("Aliendro");
        names.add("De La Cruz");
        names.add("Nacho Fernández");
        names.add("Barco");
        names.add("Beltran");
        return ResponseEntity.ok(names);
    }
}
