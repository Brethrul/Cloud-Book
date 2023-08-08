package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.UserRegistrationException;
import com.example.demo.model.User;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        // 检查用户名是否已存在
        if (userDao.existsByUsername(request.getUsername())) {
            throw new UserRegistrationException("用户名已存在");
        }

        // 创建新用户对象
        User user = new User(request.getUsername(),request.getPassword(),request.getEmail());
        // 保存用户到数据库
        userDao.save(user);

        return user;
    }
}