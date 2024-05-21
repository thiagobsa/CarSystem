package com.example.carsystem.repository;

import com.example.carsystem.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    
    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
    
    
}