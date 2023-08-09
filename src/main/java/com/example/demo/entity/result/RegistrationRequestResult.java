package com.example.demo.entity.result;

import com.example.demo.entity.User;
import com.example.demo.entity.request.RegistrationRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Immutable;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_registration_request_result")
public class RegistrationRequestResult {

    @EmbeddedId
    private RegistrationRequestResultId id;
    @MapsId("registrationRequestId")
    @ManyToOne
    @JoinColumn(name = "registration_request_id", referencedColumnName = "ID")
    private RegistrationRequest RegistrationRequest;
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    public RegistrationRequestResult(RegistrationRequest RegistrationRequest, User user, LocalDateTime createdOn) {
        this.id = new RegistrationRequestResultId(RegistrationRequest.getId(), user.getId());
        this.RegistrationRequest = RegistrationRequest;
        this.user = user;
        this.createdOn = createdOn;
    }

    public RegistrationRequestResult() {

    }

    // 其他字段和方法
    // ...
}