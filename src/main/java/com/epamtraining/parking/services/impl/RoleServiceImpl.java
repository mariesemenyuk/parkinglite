package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        if(roles == null) {
            throw new ApplicationException("Nothing to show");
        }

        return roleRepository.findAll();
    }
}
