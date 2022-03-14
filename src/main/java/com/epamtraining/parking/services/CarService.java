package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.model.CarRequest;

public interface CarService {

    CarEntity createCar(CarRequest car);

    void deleteCar(Long id);

    CarEntity approveStatus (Long id);
}
