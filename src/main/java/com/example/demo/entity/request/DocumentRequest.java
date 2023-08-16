package com.example.demo.entity.request;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_request")
public class DocumentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "operation_type")
    private int operation_type;// 0:creat 1:delete 10:shareOut 11:shareIn
    @Column(name = "doc_name",columnDefinition = "json")
    private String doc_name;
    @Column(name = "created_on")
    private LocalDateTime created_on;
    @Column(name = "status")
    private int status;// 1:Enabled 1:Disabled
    @Column(name = "doc_id")
    private int doc_id;
    public DocumentRequest(String doc_name,int doc_id,int operation_type){//创建用构造函数
        this.doc_name=doc_name;
        this.created_on=LocalDateTime.now();
        this.doc_id=doc_id;
        this.status=1;
        this.operation_type=operation_type;
    }

    public DocumentRequest() {

    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(int operation_type) {
        this.operation_type = operation_type;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }
}
