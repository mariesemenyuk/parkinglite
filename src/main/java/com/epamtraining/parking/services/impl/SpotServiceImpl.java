package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.BookedSpot;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SpotEntity> getAll() {
        return spotRepository.findAll();
    }

    @Override
    public SpotEntity createSpot(SpotRequest spotRequest) {
        SpotEntity spot = new SpotEntity();
        spot.setLocation(spotRequest.getLocation());
        spotRepository.save(spot);
        return spot;
    }

    @Override
    public void deleteSpot(Long id) {
        SpotEntity spot = spotRepository.getById(id);
        spotRepository.delete(spot);
    }

    public List<SpotEntity> getFreeSpotsForTimePeriod(LocalDateTime from, LocalDateTime to) {
        if(to.isBefore(from)) throw new ApplicationException("Entered time is incorrect");
        List<SpotEntity> spots = spotRepository.findAll();
        List<BookingEntity> bookings = bookingRepository.findAll();
        Set<SpotEntity> busySpots = new HashSet<>();

        for (BookingEntity booking: bookings) {
            if(!booking.getBookingTo().isBefore(from.plusNanos(1)) && !booking.getBookingFrom().isAfter(to.minusNanos(1))) {
                busySpots.add(booking.getSpotEntity());
            }
        }

        for (SpotEntity busySpot: busySpots) {
            spots.remove(busySpot);
        }

        return spots;
    }

    @Override
    public List<BookedSpot> getAllBookedSpots(LocalDateTime from, LocalDateTime to) {
        if(to.isBefore(from)) throw new ApplicationException("Entered time is incorrect");

        List<BookedSpot> bookedSpots = new ArrayList<>();
        List<BookingEntity> bookings = bookingRepository.findAll();
        for (BookingEntity booking: bookings) {
            BookedSpot spot = new BookedSpot();
            UserEntity user = userRepository.findById(booking.getCarEntity().getUser().getId()).get();
            spot.setEmail(user.getEmail());
            spot.setSpotLocation(booking.getSpotEntity().getLocation());
            spot.setFrom(booking.getBookingFrom());
            spot.setTo(booking.getBookingTo());
            if(!booking.getBookingFrom().isAfter(to.minusNanos(1)) && !booking.getBookingTo().isBefore(from.plusNanos(1))) {
                bookedSpots.add(spot);
            }
        }

        return bookedSpots;
    }

  /*  @Override
    public SpotEntity getSpotByBookingId(long id) {
        return spotRepository.findByBookingEntity_Id(id);
    }*/
}
