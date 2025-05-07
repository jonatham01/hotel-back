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
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "disponibility_id")
    private RoomCategoryDisponibility disponibility;
}
