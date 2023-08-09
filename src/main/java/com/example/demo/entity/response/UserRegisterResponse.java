package com.example.demo.entity.response;

import java.time.LocalDateTime;

public class UserRegisterResponse {
    private int id;
    private LocalDateTime created_on;

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }
}
