package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CarRequest {

    @Min(value = 1, message = "invalid user id")
    @NotNull(message = "user id is mandatory")
    Long userId;

    @NotBlank(message = "car number is mandatory")
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$", message = "invalid car number")
    String number;
}
