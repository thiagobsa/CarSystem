package com.example.carsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.carsystem.models.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

	
	boolean existsByLicensePlate(String licensePlate);

    Page<CarEntity> findByUserId(@Param("userId") Long userId, Pageable pageable);

}
