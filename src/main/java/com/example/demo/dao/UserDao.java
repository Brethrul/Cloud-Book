package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao {
    boolean existsByUsername(String username);

    void save(User user);
}
