package com.example.demo.entity.exception;

public class UserDoNotExistException extends Exception {
    String message;
    public UserDoNotExistException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
