package com.example.demo.entity.exception;

public class PermissionTypeException extends Exception{
    String message;
    public PermissionTypeException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
