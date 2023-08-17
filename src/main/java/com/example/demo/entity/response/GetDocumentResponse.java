package com.example.demo.entity.response;

import java.time.LocalDateTime;

public class GetDocumentResponse {
    private int doc_id;
    private String doc_name;
    private LocalDateTime created_on;
    private LocalDateTime last_accessed;
    private int status;
    private int permission_type;
    private String hashName;
    public GetDocumentResponse(int doc_id,String doc_name,LocalDateTime created_on,
                                LocalDateTime last_accessed,int status,int permission_type,String hashName){
        this.doc_id=doc_id;
        this.doc_name=doc_name;
        this.created_on=created_on;
        this.last_accessed=last_accessed;
        this.status=status;
        this.permission_type=permission_type;
        this.hashName=hashName;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
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

    public int getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(int permission_type) {
        this.permission_type = permission_type;
    }
}
