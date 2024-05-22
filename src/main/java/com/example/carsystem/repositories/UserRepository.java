package com.example.carsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carsystem.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);

    UserEntity findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}
