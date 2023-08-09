package com.example.authenticationBack.exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String detail){
        super(detail);
    }
}
