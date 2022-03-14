package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.CarRequest;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.services.CarService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public CarRequest addCar(@RequestBody @Valid CarRequest car) {
        return convertToDto(carService.createCar(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public CarRequest changeStatusToApproved(@PathVariable Long id) {
        return convertToDto(carService.approveStatus(id));
    }

    private CarRequest convertToDto(CarEntity car) {
        CarRequest carDto = modelMapper.map(car, CarRequest.class);
        return carDto;
    }
}
