package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ChangeRoleRequest {

    @NotBlank(message = "user role is mandatory")
    String role;
}
