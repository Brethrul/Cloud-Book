package com.example.demo.entity.response;

import java.time.LocalDateTime;

public class GetUserResponse {
    private String username;
    private String password;
    private LocalDateTime created_on;

    private LocalDateTime last_accessed;
    private int status;
public GetUserResponse( String username,String password,LocalDateTime created_on,LocalDateTime last_accessed,int status){
    this.username=username;
    this.created_on = created_on;
    this.password = password;
    this.last_accessed = last_accessed;
    this.status=status;
}

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

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

    public LocalDateTime getLast_accessed() {
        return last_accessed;
    }

    public void setLast_accessed(LocalDateTime last_accessed) {
        this.last_accessed = last_accessed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
