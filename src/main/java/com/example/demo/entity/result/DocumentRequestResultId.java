package com.example.demo.entity.result;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class DocumentRequestResultId implements Serializable {
    @Column(name = "request_id")
    private int  documentRequestId;
    @Column(name = "doc_id")
    private int docId;
    public DocumentRequestResultId(int documentRequestId,int docId){
        this.documentRequestId=documentRequestId;
        this.docId=docId;
    }

    public DocumentRequestResultId() {

    }
}
