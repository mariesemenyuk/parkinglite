package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class BookingRequestForProlonging {
    @Min(value = 30, message = "min time for prolonging = 30 min")
    @Max(value = 1440, message = "max time for prolonging = 24 hours")
    private int duration; //минуты
}

