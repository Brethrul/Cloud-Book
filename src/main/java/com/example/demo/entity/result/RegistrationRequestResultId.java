package com.example.demo.entity.result;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RegistrationRequestResultId implements Serializable {
@Column(name = "registration_request_id")
    private int registrationRequestId;
@Column(name = "user_id")
    private int userId;

    public RegistrationRequestResultId(int registrationRequestId, int userId) {
        this.registrationRequestId = registrationRequestId;
        this.userId = userId;
    }

    public RegistrationRequestResultId() {

    }

    // 构造函数、getter 和 setter 方法
    // ...
}