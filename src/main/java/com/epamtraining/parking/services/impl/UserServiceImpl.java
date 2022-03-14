package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerNewUserAccount(UserRequest user) {
        if (emailExists(user.getEmail())) {
            throw new ApplicationException("There is an account with that email address: " + user.getEmail());
        }
        UserEntity userNew = new UserEntity();
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        userNew.setEmail(user.getEmail());
        userNew.setRoles(Arrays.asList(roleRepository.findByName("role_user")));
        return userRepository.save(userNew);
    }

    @Override
    public UserEntity changeUserRole(Long userId, ChangeRoleRequest role) {
        UserEntity correctedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("User not found"));
        RoleEntity newRole = roleRepository.findByName(role.getRole());
        if(newRole == null) {
            throw new ApplicationException("Role doesn't exist.");
        }
        List<RoleEntity> newRoles = new ArrayList<>();
        newRoles.add(newRole);
        correctedUser.setRoles(newRoles);

        return userRepository.save(correctedUser);
    }

    private boolean emailExists(final String email) {
        return userRepository.getUserEntityByEmail(email) != null;
    }
}

