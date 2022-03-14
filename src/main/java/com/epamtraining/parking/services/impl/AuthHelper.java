package com.epamtraining.parking.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    public static boolean isOwner(String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String s = auth.getAuthorities().toString();
        if (s.substring(1, s.length() - 1).equals("role_user")) {
            String principal = auth.getPrincipal().toString();
            if (email.equals(principal)) {
                return true;
            } else return false;
        }

        return true;
    }
}
