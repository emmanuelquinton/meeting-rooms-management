package org.equinton.mrm.storage.entity;

import lombok.*;
import org.equinton.mrm.domain.model.Reservation;
import org.equinton.mrm.storage.ReservationMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "reservation" , schema = "mrm")
@Builder
@Getter
public class ReservationEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column
    private LocalDate date;
    @Column(name = "start_time" )
    private LocalTime startTime;
    @Column(name = "end_time" )
    private LocalTime endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
   // @ManyToOne(fetch = FetchType.LAZY)
    private String meetingRoom;

    public Reservation toReservation() {
        return  ReservationMapper.INSTANCE.toDomainObject(this);
    }
}
