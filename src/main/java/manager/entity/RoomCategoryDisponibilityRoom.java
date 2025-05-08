package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCategoryDisponibilityRoom {
    @EmbeddedId
    private RoomCategoryDisponibilityRoomPK id;

    @ManyToOne
    @MapsId("roomId") // <- enlaza con el campo roomId del embeddable
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    @ManyToOne
    @MapsId("roomCategoryDisponibilityId") // <- enlaza con el campo roomCategoryDisponibilityId
    @JoinColumn(name = "disponibility_id", referencedColumnName = "room_category_disponibility_id")
    private RoomCategoryDisponibility disponibility;
}
