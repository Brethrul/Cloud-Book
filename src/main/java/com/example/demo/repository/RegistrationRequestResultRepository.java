package com.example.demo.repository;

import com.example.demo.entity.result.RegistrationRequestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRequestResultRepository extends JpaRepository<RegistrationRequestResult, Long> {

}
