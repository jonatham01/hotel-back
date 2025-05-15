package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import manager.core.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Table(name = "client")
@Entity
@Data
@AllArgsConstructor
public class Client extends Person {
    private String kind;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "reservationClient")
    private List<Reservation> reservations;

    public Client(){
        this.reservations = new ArrayList<>();
    }
}

