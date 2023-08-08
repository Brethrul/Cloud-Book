package com.example.demo.request;

public class RegistrationRequest {
    private String username;
    private String password;
    private String email;

    // 构造函数
    public RegistrationRequest() {
    }

    // 带参数的构造函数
    public RegistrationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 其他方法
    // ...
}