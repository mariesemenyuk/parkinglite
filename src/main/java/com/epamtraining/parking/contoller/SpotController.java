package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.BookedSpot;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity addSpot(@RequestBody @Valid SpotRequest request) {
        return ResponseEntity.ok(spotService.createSpot(request));
    }

    @GetMapping("/free-spots")
    public List<SpotRequest> getFreeSpots(@RequestParam("from")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime,
                                        @RequestParam("to")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
        List<SpotEntity> spotBookings = spotService.getFreeSpotsForTimePeriod(fromDateTime, toDateTime);
        return spotBookings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/booked-spots")
    public List<BookedSpot> getAllBookedSpots(@RequestParam("from")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime,
                                              @RequestParam("to")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime) {
        return spotService.getAllBookedSpots(fromDateTime, toDateTime);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteSpot(@PathVariable Long id) {
        spotService.deleteSpot(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }

    private SpotRequest convertToDto(SpotEntity spot) {
        SpotRequest spotDto = modelMapper.map(spot, SpotRequest.class);
        return spotDto;
    }
}
