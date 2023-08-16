package com.example.demo.entity.result;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SharingRequestResultId implements Serializable {
    @Column(name = "request_id")
    private int requestId;
    @Column(name = "permission_id")
    private int permissionId;

    public SharingRequestResultId(int requestId, int permissionId) {
       this.requestId=requestId;
       this.permissionId=permissionId;
    }

    public SharingRequestResultId() {

    }
}
