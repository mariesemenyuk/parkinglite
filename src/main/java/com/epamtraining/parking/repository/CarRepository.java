package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Optional<CarEntity> findByNumber(String number);
}
