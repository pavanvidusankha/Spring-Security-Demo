package com.xstream.springsecuritydemo.exceptions;

public class RoleServiceException extends RuntimeException{
    public RoleServiceException(){
        super(String.format("Error on Role Service layer"));
    }
}
