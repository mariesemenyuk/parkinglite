package com.epamtraining.parking.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuthRequest {
    @NotNull (message = "email is mandatory")
    @Email(message = "should be email format")
    private String name;
    @NotNull (message = "password is mandatory")
    @Size(min = 5, message = "password length must be more than five")
    @Pattern(regexp = "^(?=.*?[#?!@$%^&*-])(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z]).{5,}", message = "password must contain letters (upper and lower case), digits and special symbols")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
