package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.exception.UserDoNotExistException;
import com.example.demo.entity.exception.UsernameExistException;
import com.example.demo.entity.exception.UsernameOrPasswordWrongException;
import com.example.demo.entity.request.LoginRequest;
import com.example.demo.entity.response.GetUserResponse;
import com.example.demo.entity.response.LoginResponse;
import com.example.demo.entity.result.RegistrationRequestResult;
import com.example.demo.entity.util.JwtUtil;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.repository.RegistrationRequestResultRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.request.RegistrationRequest;
import com.example.demo.entity.response.UserRegisterResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RegistrationRequestRepository registrationRequestRepository;
    private final RegistrationRequestResultRepository registrationRequestResultRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RegistrationRequestRepository registrationRequestRepository,
                           RegistrationRequestResultRepository registrationRequestResultRepository
    ) {
        this.userRepository = userRepository;
        this.registrationRequestRepository=registrationRequestRepository;
        this.registrationRequestResultRepository=registrationRequestResultRepository;
    }
    @Override
    public UserRegisterResponse registerUser(RegistrationRequest request)throws UsernameExistException {
        //存储注册请求
        request.setCreatedOn(LocalDateTime.now());
        request.setStatus(0);
        registrationRequestRepository.save(request);

        // 检查用户名是否已存在
        if (userRepository.findByUsername(request.getUsername()) != null) {
            // 用户名已存在，可以抛出一个自定义的异常或进行其他处理
            registrationRequestRepository.updateStatusById(-1,request.getId());//失败 status改为-1
            throw new UsernameExistException("Username already exists");
        }
        registrationRequestRepository.updateStatusById(1,request.getId());//成功 status改为1
        //存储用户信息
        User user = new User(request.getUsername(),request.getPassword(),request.getDisplay_name());
        // 保存用户到数据库
        userRepository.save(user);

        //如果用户注册成功，保存注册信息到表user_registration_request_result
        registrationRequestResultRepository.save(new RegistrationRequestResult(request,user,LocalDateTime.now()));
        //生成响应
        UserRegisterResponse response = new UserRegisterResponse();
        response.setId(user.getId());
        response.setCreated_on(user.getCreatedOn());
        return response;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) throws UserDoNotExistException, UsernameOrPasswordWrongException {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new UserDoNotExistException("Username do not exist");
        } else if (user !=userRepository.findByPassword(request.getPassword())) {
            throw new UsernameOrPasswordWrongException("Username or password do not correct");
        }
        userRepository.updateLastAccessed(user.getId(),LocalDateTime.now());//更新最后登录时间

        //生成响应
        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setLast_accessed(user.getLastAccessed());
        response.setToken(JwtUtil.generateToken(user.getUsername(), 1800000));
        return response;
    }

    @Override
    public GetUserResponse getUserInfo(int Id) throws UserDoNotExistException {
        User user = userRepository.findById(Id);
        if (user == null) {
            throw new UserDoNotExistException("Username do not exist");
        }
        GetUserResponse response = new GetUserResponse(user.getUsername(),user.getPassword(),user.getCreatedOn(),user.getLastAccessed(),user.getStatus());
        return response;
    }
}