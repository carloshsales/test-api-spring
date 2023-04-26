package com.api.authentication.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String msg){
        super(msg);
    }
}
