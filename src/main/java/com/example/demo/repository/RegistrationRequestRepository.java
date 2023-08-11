package com.example.demo.repository;

import com.example.demo.entity.request.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE RegistrationRequest r SET r.status = :status WHERE r.id = :id")
    void updateStatusById(@Param("status") int status, @Param("id") long id);
}
