package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.services.BookingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public BookingRequest createBooking(@RequestBody @Valid BookingRequest bookingRequest) {
        return convertToDto(bookingService.createBooking(bookingRequest));
    }

    @PutMapping("/{bookingId}")
    public BookingRequest prolongBooking(@RequestBody @Valid BookingRequestForProlonging request, @PathVariable Long bookingId) {
        return convertToDto(bookingService.prolongBooking(request, bookingId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity cancelBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }

    private BookingRequest convertToDto(BookingEntity booking) {
        BookingRequest bookingDto = modelMapper.map(booking, BookingRequest.class);
        return bookingDto;
    }
}
