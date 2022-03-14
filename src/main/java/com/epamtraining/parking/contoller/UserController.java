package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/")
public class UserController {
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{userId}")
    public UserRequest changeUserRole (@RequestBody ChangeRoleRequest role, @PathVariable Long userId) {
        return convertToDto(userService.changeUserRole(userId, role));
    }

    private UserRequest convertToDto(UserEntity user) {
        UserRequest userDto = modelMapper.map(user, UserRequest.class);
        return userDto;
    }
}