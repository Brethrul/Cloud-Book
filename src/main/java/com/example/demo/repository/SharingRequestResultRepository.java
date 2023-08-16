package com.example.demo.repository;

import com.example.demo.entity.result.SharingRequestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharingRequestResultRepository extends JpaRepository<SharingRequestResult,Long> {
}
