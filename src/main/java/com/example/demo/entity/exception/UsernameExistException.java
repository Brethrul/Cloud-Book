package com.example.demo.entity.exception;

public class UsernameExistException extends Exception {

    String message;
    public UsernameExistException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
