package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.disabled.RoomOccupancy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_description")
    private String description;

    @Column(name = "room_category_id")
    private Integer roomCategoryId;

    @Column(name = "room_category_hotel")
    private Integer roomCategoryHotelId;

    @Column(name = "room_status") //enabled //desable
    private String roomStatus;

    @ManyToOne()
    @JoinColumn(name="room_category_hotel")
    private Hotel hotel;

    @ManyToOne()
    @JoinColumn(name="room_category_id")
    private RoomCategory roomCategory;

    @OneToMany(mappedBy = "room")
    private List<RoomOccupancy> availabilityRooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
    //@OneToMany(mappedBy = "reception_room_id")
    //private List<ReceptionRoom> receptionRooms;


}
