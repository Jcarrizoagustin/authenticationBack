package com.example.authenticationBack.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException(String detail){
        super(detail);
    }
}
