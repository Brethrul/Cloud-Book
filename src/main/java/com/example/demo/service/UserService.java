package com.example.demo.service;



import com.example.demo.model.User;
import com.example.demo.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(RegistrationRequest request);
}
