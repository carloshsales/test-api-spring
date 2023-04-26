package com.api.authentication.exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String msg){
        super(msg);
    }
}
