package com.example.demo.service.impl;

import com.example.demo.entity.Document;
import com.example.demo.entity.Permission;
import com.example.demo.entity.request.DocumentRequest;
import com.example.demo.entity.response.CreatDocumentResponse;
import com.example.demo.entity.response.GetDocumentResponse;
import com.example.demo.entity.result.DocumentRequestResult;
import com.example.demo.repository.*;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {
    private final PermissionRepository permissionsRepository;
    private final DocumentRequestRepository documentRequestRepository;
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final DocumentRequestResultRepository documentRequestResultRepository;
    @Autowired
    public DocumentServiceImpl(PermissionRepository permissionsRepository,
                               DocumentRequestRepository documentRequestRepository,
                               DocumentRepository documentRepository,
                               UserRepository userRepository,
                               DocumentRequestResultRepository documentRequestResultRepository){
        this.permissionsRepository=permissionsRepository;
        this.documentRequestRepository=documentRequestRepository;
        this.documentRepository=documentRepository;
        this.userRepository=userRepository;
        this.documentRequestResultRepository=documentRequestResultRepository;
    }
    @Override
    public List<GetDocumentResponse> getDocument(int userId) {
        List<GetDocumentResponse> responses = new ArrayList<>();

        List<Permission> permissions = permissionsRepository.findByUserId(userId);
        for (Permission permission:permissions ) {
            Document document=permission.getDocument();
            responses.add(new GetDocumentResponse(document.getId(),document.getDocName(),document.getCreatedOn(),
                                                  document.getLastAccessed(),document.getStatus(),permission.getPermissionType()));
        }
        return responses;
    }

    @Override
    public CreatDocumentResponse creatDocument(DocumentRequest request) {
        //保存请求
        documentRequestRepository.save(request);
        //存储新文档
        Document document = new Document(request.getDoc_name());
        documentRepository.save(document);
        //为用户生成权限：admin, 并存储
        Permission permission= new Permission(userRepository.findById(request.getUser_id()),document,0);
        permissionsRepository.save(permission);
        //存储结果 document_request_result
        documentRequestResultRepository.save(new DocumentRequestResult(request,document));
        //生成响应
        CreatDocumentResponse response = new CreatDocumentResponse(document.getId());
        response.setCreated_on(document.getCreatedOn());
        return response;
    }
}
