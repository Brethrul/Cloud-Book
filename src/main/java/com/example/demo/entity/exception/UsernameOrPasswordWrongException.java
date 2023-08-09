package com.example.demo.entity.exception;

public class UsernameOrPasswordWrongException extends Exception{
    String message;
    public UsernameOrPasswordWrongException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
