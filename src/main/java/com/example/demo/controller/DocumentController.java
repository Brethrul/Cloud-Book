package com.example.demo.controller;

import com.example.demo.entity.exception.PermissionTypeException;
import com.example.demo.entity.exception.UsernameExistException;
import com.example.demo.entity.request.DocumentRequest;
import com.example.demo.entity.request.RegistrationRequest;
import com.example.demo.entity.request.SharingRequest;
import com.example.demo.entity.response.CreatDocumentResponse;
import com.example.demo.entity.response.GetDocumentResponse;
import com.example.demo.entity.response.UserRegisterResponse;
import com.example.demo.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{user_id}/get")
    public ResponseEntity<List<GetDocumentResponse>> getDocuments(@PathVariable int user_id) {
        try {
            List<GetDocumentResponse> responses = documentService.getDocument(user_id);
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping(value = "/{user_id}/creat", produces = "application/json")
    public ResponseEntity<CreatDocumentResponse> creatDocument(@PathVariable("user_id") int user_id, @RequestBody DocumentRequest request) {
        try {
            if (user_id <= 0) {
                throw new IllegalArgumentException("Invalid user_id");
            }
            // 设置 userId 属性
            request.setUser_id(user_id);
            CreatDocumentResponse response = documentService.creatDocument(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping(value = "/{user_id}/delete", produces = "application/json")
    public ResponseEntity<Object> deleteDocument(@PathVariable("user_id") int user_id, @RequestBody DocumentRequest request) {
        try {
            request.setUser_id(user_id);
            documentService.deleteDocument(request);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (PermissionTypeException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

    @PostMapping(value = "/{user_id}/share", produces = "application/json")
    public ResponseEntity<Object> shareOutDocument(@PathVariable("user_id") int user_id, @RequestBody SharingRequest request) {
        try {
            documentService.shareOutDocument(user_id,request);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
