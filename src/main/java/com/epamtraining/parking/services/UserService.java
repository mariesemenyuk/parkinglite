package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    public UserEntity registerNewUserAccount(UserRequest user);
    public UserEntity changeUserRole(Long userId, ChangeRoleRequest role);
}
