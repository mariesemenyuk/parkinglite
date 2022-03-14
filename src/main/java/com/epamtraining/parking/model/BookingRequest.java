package com.epamtraining.parking.model;

import com.epamtraining.parking.annotations.BookingDuration;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@BookingDuration(message = "invalid duration for booking (min period = 30 minutes, max period = 24 hours)")
public class BookingRequest {
    @Min(value = 1, message = "invalid car id")
    @NotNull(message = "car id is mandatory")
    private long carId;
    @Min(value = 1, message = "invalid spot id")
    @NotNull(message = "spot id is mandatory")
    private long spotId;
    @NotNull(message = "time /from/ - please, enter the time")
    @Future(message = "time /from/ - please, enter the actual time in the future")
    private LocalDateTime from;
    @NotNull(message = "time /to/ - please, enter the time")
    @Future(message = "time /to/ - please, enter the actual time in the future")
    private LocalDateTime to;
}
