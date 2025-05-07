package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import manager.core.Person;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "guest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest extends Person {

    @Column(name="guest_kind")
    private String guestKind;

    @Column(name="guest_birthdate")
    private LocalDate guestBirthDate;


    ///necessary?
    @Column(name="guest_start_date")
    private LocalDate guestStartDate;
    @Column(name="guest_end_date")
    private LocalDate guestEndDate;

    //Link with many to many

    @ManyToOne
    @JoinColumn(name = "guest_hotel_id")
    private Hotel hotel;

}
