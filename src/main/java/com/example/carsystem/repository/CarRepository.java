package com.example.carsystem.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.carsystem.model.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Page<CarEntity> findByUserId(@Param("userId") Long userId, Pageable pageable);

    boolean existsByLicensePlate(String licensePlate);
    
}
