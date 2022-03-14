package com.epamtraining.parking.model;

import com.epamtraining.parking.annotations.PasswordMatch;
import com.epamtraining.parking.domain.entity.UserEntity;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@PasswordMatch(message = "password and password confirmation must match")
public class UserRequest {
    private long id;

    @NotBlank(message = "email is mandatory")
    @Email(message = "should be email format")
    private String email;

    @NotBlank(message = "password is mandatory")
    @Size(min = 5, message = "password length must be more than five")
    @Pattern(regexp = "^(?=.*?[#?!@$%^&*-])(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z]).{5,}", message = "password must contain letters (upper and lower case), digits and special symbols")
    private String password;

    @NotBlank(message = "password confirmation is mandatory")
    private String matchingPassword;

    public static UserRequest toModel(UserEntity userEntity) {
        UserRequest model = new UserRequest();
        model.setId(userEntity.getId());
        model.setEmail(userEntity.getEmail());
        return model;
    }
}


