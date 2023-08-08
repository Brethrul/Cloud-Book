package com.example.demo.controller;

// UserController.java

import com.example.demo.exception.UserRegistrationException;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationRequest request) {
        // 调用 UserService 的注册方法进行用户注册
        try {
            return userService.registerUser(request);
        } catch (UserRegistrationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @GetMapping("/login")
    public User loginUser(@RequestBody RegistrationRequest request) {
        return null;
    }
}

