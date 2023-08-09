package com.example.demo.entity.request;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_registration_request")
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "created_on", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdOn;
    @Column(name = "status")//注册请求的状态: 0 new,1 processing,2 processed
    private int status;
    // 构造函数
    public RegistrationRequest() {
    }

    // 带参数的构造函数
    public RegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter 和 Setter 方法
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 其他方法
    // ...
}