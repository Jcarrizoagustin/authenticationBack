package com.example.authenticationBack.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String detail){
        super(detail);
    }
}