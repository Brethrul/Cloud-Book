package com.example.demo.entity;

// UserController.java

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String username;

    @Column(name = "user_passsword")
    private String password;

    @Column(name = "created_on", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdOn;

    @Column(name = "last_accessed",nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastAccessed;

    @Column(name = "status")
    private int status;

    public User(String username, String password) {//注册时候用的构造函数
        this.username = username;
        this.password = password;
        this.createdOn = LocalDateTime.now();
        this.status = 1;
        this.lastAccessed = LocalDateTime.now();
    }

    public User() {

    }

    // getter 和 setter 方法

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}