package com.example.demo.service;

import com.example.demo.entity.exception.PermissionTypeException;
import com.example.demo.entity.request.DocumentRequest;
import com.example.demo.entity.request.SharingRequest;
import com.example.demo.entity.response.CreatDocumentResponse;
import com.example.demo.entity.response.GetDocumentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentService {

    List<GetDocumentResponse> getDocument(int userId);

    CreatDocumentResponse creatDocument(DocumentRequest request);

    public void deleteDocument(DocumentRequest request) throws PermissionTypeException;

    void shareOutDocument(int user_id, SharingRequest request) throws Exception;
}
