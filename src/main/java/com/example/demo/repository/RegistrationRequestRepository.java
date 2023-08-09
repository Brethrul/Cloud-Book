package com.example.demo.repository;

import com.example.demo.entity.request.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
}
