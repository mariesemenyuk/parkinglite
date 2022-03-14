package com.epamtraining.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "spot", schema = "public")
@EqualsAndHashCode(exclude = {"bookings"})
@ToString(exclude = {"bookings"})
public class SpotEntity implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "spotEntity")
    @JsonIgnore
    private List<BookingEntity> bookings;
    private String location;

    @Override
    public int compareTo(Object o) {
        SpotEntity o2 = (SpotEntity) o;
        return this.getId().intValue() - o2.getId().intValue();
    }
}
