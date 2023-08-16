package com.example.demo.repository;

import com.example.demo.entity.Document;
import com.example.demo.entity.Permission;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
     List<Permission> findByUserId(int userId);
     Permission findByDocument(Document document);//一个docId只能由一个userId所有

     Permission findByDocumentAndUser(Document document, User byId);
}
