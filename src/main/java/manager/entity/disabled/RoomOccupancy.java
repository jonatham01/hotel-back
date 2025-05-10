package manager.entity.disabled;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.Reservation;
import manager.entity.Room;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name="room_availability")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomOccupancy {

    @EmbeddedId
    private RoomOccupancyPK roomRoomOccupancyPK;

    @Column(name="room_occupancy_status")
    private String roomOccupancyStatus;
    //crear  clase CleanRoom
    //@Column(name="room_availability_clean_status")
    //private String roomAvailabilityCleanStatus;

    //private String paymentStatus;

    @Column(name= "room_occupancy_note")
    private String roomOccupancyNote;

    @Column(name="room_occupancy_change_datetime")
    private LocalDateTime roomOccupancyUltimatedChange;


   // @Column(name = "room_availability_room_id")
   // private Integer roomId;

//    @ManyToOne()
//    @JoinColumn(name="occupancy_room")
//    private Room room;

//    @ManyToOne()
//    @JoinColumn(name="occupancy_reservation_id")
//    private Reservation reservation;


}
