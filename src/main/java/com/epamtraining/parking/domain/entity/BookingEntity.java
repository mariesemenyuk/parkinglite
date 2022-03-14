package com.epamtraining.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking", schema = "public")
@EqualsAndHashCode(exclude = {"spotEntity"})
@ToString(exclude = {"spotEntity"})
@Accessors(chain = true)
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime bookingFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime bookingTo;

    @OneToOne
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private CarEntity carEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "spot_id")
    private SpotEntity spotEntity;

}
