package com.example.demo.controller;

// UserController.java

import com.example.demo.entity.exception.UserDoNotExistException;
import com.example.demo.entity.exception.UsernameExistException;
import com.example.demo.entity.exception.UsernameOrPasswordWrongException;
import com.example.demo.entity.request.LoginRequest;
import com.example.demo.entity.response.GetUserResponse;
import com.example.demo.entity.response.LoginResponse;
import com.example.demo.entity.response.UserRegisterResponse;
import com.example.demo.entity.request.RegistrationRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //用户注册方法 入参用@RequestParam表示
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody RegistrationRequest request) {
        // 调用 UserService 的注册方法进行用户注册
        try {
            UserRegisterResponse response = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UsernameExistException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @GetMapping(value = "/login", produces = "application/json")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {

        try{
            LoginResponse response=userService.loginUser(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (UserDoNotExistException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }catch (UsernameOrPasswordWrongException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
}
    @GetMapping(value = "{user_id}/info" ,produces = "application/json")
    public ResponseEntity<GetUserResponse> getUserInfo(@PathVariable("user_id") int Id) {
        // 根据userId获取用户信息的逻辑
        try{
            GetUserResponse response=userService.getUserInfo(Id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (UserDoNotExistException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}

