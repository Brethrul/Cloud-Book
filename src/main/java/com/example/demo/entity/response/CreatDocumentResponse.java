package com.example.demo.entity.response;

import java.time.LocalDateTime;

public class CreatDocumentResponse {
    private int doc_id;
    private LocalDateTime created_on;
    public CreatDocumentResponse(int doc_id){
        this.doc_id=doc_id;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }
}
