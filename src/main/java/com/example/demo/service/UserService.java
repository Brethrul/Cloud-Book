package com.example.demo.service;



import com.example.demo.entity.User;
import com.example.demo.entity.exception.UserDoNotExistException;
import com.example.demo.entity.exception.UsernameExistException;
import com.example.demo.entity.exception.UsernameOrPasswordWrongException;
import com.example.demo.entity.request.LoginRequest;
import com.example.demo.entity.request.RegistrationRequest;
import com.example.demo.entity.response.GetUserResponse;
import com.example.demo.entity.response.LoginResponse;
import com.example.demo.entity.response.UserRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     UserRegisterResponse registerUser(RegistrationRequest request )throws UsernameExistException;
     LoginResponse loginUser(LoginRequest request) throws UserDoNotExistException, UsernameOrPasswordWrongException;

     GetUserResponse getUserInfo(int Id) throws UserDoNotExistException;
}
