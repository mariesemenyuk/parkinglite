package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).get();

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user by email" + user.getEmail());
        }

        return new MyUserDetails(user);
    }

}
