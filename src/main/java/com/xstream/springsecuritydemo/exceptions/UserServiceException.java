package com.xstream.springsecuritydemo.exceptions;

public class UserServiceException extends RuntimeException{
    public UserServiceException(){
        super(String.format("Error on User Service layer"));
    }
}
