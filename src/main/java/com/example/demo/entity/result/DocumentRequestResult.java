package com.example.demo.entity.result;

import com.example.demo.entity.Document;
import com.example.demo.entity.request.DocumentRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_request_result")
public class DocumentRequestResult {
    @EmbeddedId
    private DocumentRequestResultId id;
    @MapsId("documentRequestId")
    @ManyToOne
    @JoinColumn(name = "request_id",referencedColumnName = "ID")
    private DocumentRequest documentRequest;
    @MapsId("docId")
    @ManyToOne
    @JoinColumn(name = "doc_id",referencedColumnName = "id")
    private Document document;
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    public DocumentRequestResult(DocumentRequest documentRequest,Document document){
        this.id=new DocumentRequestResultId(documentRequest.getId(),document.getId());
        this.documentRequest=documentRequest;
        this.document=document;
        this.createdOn=LocalDateTime.now();
    }

    public DocumentRequestResult() {

    }
}
