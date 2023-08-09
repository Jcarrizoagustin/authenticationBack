package com.example.authenticationBack.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {
    private static final String DETAIL = "The user is not registered";
    public UserNotFoundException(String detail){
        super(DETAIL + " " + detail);
    }
}
