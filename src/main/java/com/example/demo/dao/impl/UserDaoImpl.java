package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public void save(User user) {

    }
}
