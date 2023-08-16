package com.example.demo.entity.result;

import com.example.demo.entity.Permission;
import com.example.demo.entity.User;
import com.example.demo.entity.request.DocumentRequest;
import com.example.demo.entity.request.RegistrationRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_sharing_request_result_table")
public class SharingRequestResult {
    @EmbeddedId
    private SharingRequestResultId id;
    @MapsId("requestId")
    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private DocumentRequest documentRequest;
    @MapsId("permissionId")
    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    public SharingRequestResult(DocumentRequest documentRequest,Permission permission) {
        this.id = new SharingRequestResultId(documentRequest.getId(),permission.getId());
        this.documentRequest = documentRequest;
        this.permission = permission;
        this.createdOn = LocalDateTime.now();
    }

    public SharingRequestResult() {

    }
}
