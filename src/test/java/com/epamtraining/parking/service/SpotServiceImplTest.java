package com.epamtraining.parking.service;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.BookedSpot;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.SpotServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpotServiceImplTest {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    SpotRepository spotRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    SpotServiceImpl spotService;
    @Captor
    ArgumentCaptor<SpotEntity> spotEntityCaptor;

    @Test
    public void getAllSpotsTest() {
        SpotEntity spot1 = new SpotEntity().setLocation("200");
        SpotEntity spot2 = new SpotEntity().setLocation("201");
        List<SpotEntity> spots = new ArrayList<>(Arrays.asList(spot1,spot2));
        when(spotRepository.findAll()).thenReturn(spots);
        assertEquals(spots, spotService.getAll());
    }

    @Test
    public void createSpotTest() {
        SpotRequest request = new SpotRequest();
        request.setLocation("200");
        when(spotRepository.save(any())).thenAnswer(e -> e.getArgument(0));
        spotService.createSpot(request);
        verify(spotRepository, times(1)).save(spotEntityCaptor.capture());
        SpotEntity entity = spotEntityCaptor.getValue();
        assertEquals(request.getLocation(), entity.getLocation());
    }


    @Test
    public void deleteSpotTest() {
        SpotEntity entity = mock(SpotEntity.class);
        entity.setLocation("201");
        when(spotRepository.getById(any())).thenReturn(entity);
        spotService.deleteSpot(0L);
        verify(spotRepository, times(1)).delete(entity);
    }


    @Test
    public void getFreeSpotsForTimePeriodTest() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMinutes(40);
        SpotEntity spot1 = new SpotEntity().setLocation("200");
        SpotEntity spot2 = new SpotEntity().setLocation("201");
        SpotEntity spot3 = new SpotEntity().setLocation("202");
        SpotEntity spot4 = new SpotEntity().setLocation("203");
        List<SpotEntity> spots = new ArrayList<>(Arrays.asList(spot1,spot2,spot3,spot4));
        BookingEntity booking1 = new BookingEntity()
                .setSpotEntity(spot1)
                .setBookingFrom(LocalDateTime.now().plusMinutes(10))
                .setBookingTo(LocalDateTime.now().plusMinutes(20));
        BookingEntity booking2 = new BookingEntity()
                .setSpotEntity(spot2)
                .setBookingFrom(LocalDateTime.now().plusMinutes(20))
                .setBookingTo(LocalDateTime.now().plusMinutes(30));
        List<BookingEntity> bookings = new ArrayList<>(Arrays.asList(booking1,booking2));

        when(spotRepository.findAll()).thenReturn(spots);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<SpotEntity> freeSpotsForTimePeriod = spotService.getFreeSpotsForTimePeriod(from, to);

        verify(spotRepository, times(1)).findAll();
        verify(bookingRepository, times(1)).findAll();

        assertEquals(spot3,freeSpotsForTimePeriod.get(0));
        assertEquals(spot4,freeSpotsForTimePeriod.get(1));
        assertEquals(2,freeSpotsForTimePeriod.size());

    }



    @Test
    public void getAllBookedSpotsTest() {
        BookedSpot bookedSpot = new BookedSpot();
        UserEntity user1 = new UserEntity().setId(1L).setEmail("100");

        CarEntity car1 = new CarEntity()
                .setUser(user1);

        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMinutes(40);
        SpotEntity spot1 = new SpotEntity().setLocation("200");
        SpotEntity spot2 = new SpotEntity().setLocation("201");
        SpotEntity spot3 = new SpotEntity().setLocation("202");
        SpotEntity spot4 = new SpotEntity().setLocation("203");
        List<SpotEntity> spots = new ArrayList<>(Arrays.asList(spot1,spot2,spot3,spot4));
        BookingEntity booking1 = new BookingEntity()
                .setSpotEntity(spot1)
                .setBookingFrom(LocalDateTime.now().plusMinutes(10))
                .setBookingTo(LocalDateTime.now().plusMinutes(20))
                .setCarEntity(car1);

        List<BookingEntity> bookings = new ArrayList<>(Arrays.asList(booking1));

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user1));
//        when(userRepository.findById(booking2.getCarEntity().getUser().getId())).thenReturn(Optional.of(user2));
        List<BookedSpot> allBookedSpots = spotService.getAllBookedSpots(from, to);
        verify(bookingRepository,times(1)).findAll();
        verify(userRepository,times(1)).findById(any());

        assertEquals(1,allBookedSpots.size());
        assertEquals("200",allBookedSpots.get(0).getSpotLocation());


    }

    @Test
    public void getExceptionWhenFromIsBeforeTo() {
        LocalDateTime from = LocalDateTime.now().plusMinutes(30);
        LocalDateTime to = LocalDateTime.now();
        assertThrows(ApplicationException.class, () -> spotService.getFreeSpotsForTimePeriod(from, to));
    }

}



