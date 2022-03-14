//package com.epamtraining.parking.service;
//
//import com.epamtraining.parking.domain.entity.CarEntity;
//import com.epamtraining.parking.domain.entity.UserEntity;
//import com.epamtraining.parking.domain.exception.ApplicationException;
//import com.epamtraining.parking.model.CarRequest;
//import com.epamtraining.parking.repository.CarRepository;
//import com.epamtraining.parking.repository.UserRepository;
//import com.epamtraining.parking.services.impl.CarServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static com.epamtraining.parking.services.impl.AuthHelper.isOwner;
//
//@ExtendWith(MockitoExtension.class)
//public class CarServiceImplTest {
//    @Mock
//    CarRepository carRepository;
//
//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    CarServiceImpl carService;
//
////    @Test
////    public void carCreateTest(){
////        CarRequest request =
////    }
//
//
//    public CarEntity createCar(CarRequest car) {
//        UserEntity user = userRepository.findById(car.getUserId()).get();
//        if(isOwner(user.getEmail())) {
//            CarEntity carEntity = new CarEntity();
//            carEntity.setUser(user)
//                    .setNumber(car.getNumber())
//                    .setCurrentStatus(CarEntity.Status.requested);
//            return carRepository.save(carEntity);
//        } else throw new ApplicationException("User is allowed to add only his own car.");
//    }
//
//    @Override
//    public void deleteCar(Long id) {
//        CarEntity car = carRepository.getById(id);
//        carRepository.delete(car);
//    }
//
//    @Override
//    public CarEntity approveStatus(Long id) {
//        CarEntity car = carRepository.getById(id);
//        if (car.getCurrentStatus() == CarEntity.Status.approved) {
//            throw new ApplicationException("Car is already approved");
//        } else car.setCurrentStatus(CarEntity.Status.approved);
//        return carRepository.save(car);
//    }
//}
