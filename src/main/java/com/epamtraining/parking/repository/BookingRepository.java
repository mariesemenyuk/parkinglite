package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    //Iterable<BookingEntity> findBookingEntityByFromAfterAndToBefore(LocalDateTime from, LocalDateTime to);

    List<BookingEntity> findBookingEntitiesByBookingFromAfterOrBookingTo(LocalDateTime to, LocalDateTime from);

    BookingEntity findBookingEntityByCarEntity(String carNumber);

    BookingEntity findBookingEntityBySpotEntity(long id);
}
