package com.example.demo.service.impl;

import com.example.demo.entity.Document;
import com.example.demo.entity.Permission;
import com.example.demo.entity.exception.PermissionTypeException;
import com.example.demo.entity.request.DocumentRequest;
import com.example.demo.entity.request.SharingRequest;
import com.example.demo.entity.response.CreatDocumentResponse;
import com.example.demo.entity.response.GetDocumentResponse;
import com.example.demo.entity.result.DocumentRequestResult;
import com.example.demo.entity.result.SharingRequestResult;
import com.example.demo.repository.*;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final SharingRequestResultRepository sharingRequestResultRepository;
    @Autowired
    public DocumentServiceImpl(PermissionRepository permissionsRepository,
                               DocumentRequestRepository documentRequestRepository,
                               DocumentRepository documentRepository,
                               UserRepository userRepository,
                               DocumentRequestResultRepository documentRequestResultRepository,
                               SharingRequestResultRepository sharingRequestResultRepository){
        this.permissionsRepository=permissionsRepository;
        this.documentRequestRepository=documentRequestRepository;
        this.documentRepository=documentRepository;
        this.userRepository=userRepository;
        this.documentRequestResultRepository=documentRequestResultRepository;
        this.sharingRequestResultRepository=sharingRequestResultRepository;
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
        request.setCreated_on(LocalDateTime.now());
        request.setOperation_type(0);//creat
        request.setStatus(1);
        //存储新文档
        Document document = new Document(request.getDoc_name());
        documentRepository.save(document);

        request.setDoc_id(document.getId());
        documentRequestRepository.save(request);

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

    @Override
    public void deleteDocument(DocumentRequest request) throws PermissionTypeException {
        //保存请求
        Document document = documentRepository.findById(request.getDoc_id());
        request.setDoc_name(document.getDocName());
        request.setCreated_on(LocalDateTime.now());
        request.setOperation_type(1);//delete
        request.setStatus(1);//enabled
        documentRequestRepository.save(request);

        //赋予文档删除标记
        if((permissionsRepository.findByDocumentAndUser(document,userRepository.findById(request.getUser_id()))==null)||(
                permissionsRepository.findByDocumentAndUser(document,userRepository.findById(request.getUser_id())).getPermissionType()!=0)){//如果没有管理权限
            throw new PermissionTypeException("User don't have permission");
        }
        document.setLastAccessed(LocalDateTime.now());
        document.setStatus(0);//deleted

        //存储结果 document_request_result
        documentRequestResultRepository.save(new DocumentRequestResult(request,document));
    }

    @Override
    public void shareOutDocument(int user_id, SharingRequest request) throws Exception{
        Document document = documentRepository.findById(request.getDoc_id());
        //存储请求
        DocumentRequest documentRequest = new DocumentRequest(document.getDocName(),
                                                request.getDoc_id(),10);
        documentRequestRepository.save(documentRequest);

        if((permissionsRepository.findByDocumentAndUser(document,userRepository.findById(user_id))==null)||(
                permissionsRepository.findByDocumentAndUser(document,userRepository.findById(user_id)).getPermissionType()!=0)){//如果没有管理权限
            throw new PermissionTypeException("User don't have permission");
        }
        //为目标user赋予doc的指定权限
        Permission permission = new Permission(userRepository.findById(request.getTarget_user_id()),
                                                documentRepository.findById(request.getDoc_id()),
                                                request.getPermission_type());
        permissionsRepository.save(permission);

        //存储结果 表document_sharing_request_result_table
        SharingRequestResult result = new SharingRequestResult(documentRequest,permission);
        sharingRequestResultRepository.save(result);

    }
}
