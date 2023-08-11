package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "doc_name")
    private String docName;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;
    @Column(name = "status")
    private int status;// 1:created 0:deleted
    public Document(String docName){
        this.docName=docName;
        this.createdOn=LocalDateTime.now();
        this.lastAccessed=LocalDateTime.now();
        this.status=1;
    }

    public Document() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
