package com.epamtraining.parking.schedulingtasks;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.services.AuthenticationUtil;
import com.epamtraining.parking.services.BookingService;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledTasks {
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @Scheduled(fixedRate = 60000)
    public void checkForBookingToStop() {
        AuthenticationUtil.configureAuthentication("role_admin");

        List<BookingEntity> all = bookingRepository.findAll();
        for (BookingEntity booking : all) {
            if (booking.getBookingTo().isBefore(LocalDateTime.now())) {
                bookingService.deleteBooking(booking.getId());
            }
        }
        AuthenticationUtil.clearAuthentication();

    }
}
