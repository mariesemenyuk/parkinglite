package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.CarRequest;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.epamtraining.parking.services.impl.AuthHelper.isOwner;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private UserRepository userRepository;

    @Override
    public CarEntity createCar(CarRequest car) {
        UserEntity user = userRepository.findById(car.getUserId()).get();
        if(isOwner(user.getEmail())) {
            CarEntity carEntity = new CarEntity();
            carEntity.setUser(user)
                    .setNumber(car.getNumber())
                    .setCurrentStatus(CarEntity.Status.requested);
            return carRepository.save(carEntity);
        } else throw new ApplicationException("User is allowed to add only his own car.");
    }

    @Override
    public void deleteCar(Long id) {
        CarEntity car = carRepository.getById(id);
        carRepository.delete(car);
    }

    @Override
    public CarEntity approveStatus(Long id) {
        CarEntity car = carRepository.getById(id);
        if (car.getCurrentStatus() == CarEntity.Status.approved) {
            throw new ApplicationException("Car is already approved");
        } else car.setCurrentStatus(CarEntity.Status.approved);
        return carRepository.save(car);
    }
}
