package com.example.demo.exception;

public class UserRegistrationException extends RuntimeException {
    private String err;
    public UserRegistrationException(String err) {
        this.err=err;
    }

    public String getMessage() {
        return "Some error in registration";
    }
}
