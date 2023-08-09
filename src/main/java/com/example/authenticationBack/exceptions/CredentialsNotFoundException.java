package com.example.authenticationBack.exceptions;

public class CredentialsNotFoundException extends RuntimeException{

    public CredentialsNotFoundException(String detail){
        super(detail);
    }
}
