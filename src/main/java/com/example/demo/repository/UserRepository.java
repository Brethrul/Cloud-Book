package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByPassword(String password);

    User findById(int Id);
    //登陆时更新最后访问时间
    @Modifying
    @Query("UPDATE User u SET u.lastAccessed = :newLastAccessed WHERE u.id = :userId")
    void updateLastAccessed(@Param("userId") int userId, @Param("newLastAccessed") LocalDateTime newLastAccessed);
}