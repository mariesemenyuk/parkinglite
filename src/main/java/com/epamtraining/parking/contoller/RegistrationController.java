package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/registration/")
public class RegistrationController {
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public UserRequest registerUserAccount(@RequestBody @Valid UserRequest user) {
        return convertToDto(userService.registerNewUserAccount(user));
    }

    private UserRequest convertToDto(UserEntity user) {
        UserRequest userDto = modelMapper.map(user, UserRequest.class);
        return userDto;
    }
}
