package manager.entity.disabled;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.Guest;
import manager.entity.Room;

import java.time.LocalDate;

@Entity
//@Table(name = "reception_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reception_id")
    private int receptionId;

    @Column(name = "reception_date_time")
    private LocalDate receptionDate;

    @Column(name = "reception_check_kind")
    private String receptionCheckKind;

//    @ManyToOne
//    @JoinColumn(name = "reception_room_id")
//    private Room receptionRoom;
//
//    @ManyToOne
//    @JoinColumn(name = "reception_guest_id")
//    private Guest receptionGuest;

}
