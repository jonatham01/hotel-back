package manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Embeddable
@Data
@NoArgsConstructor
@Builder
public class RoomCategoryDisponibilityRoomPK {
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "disponibility_id")
    private Long roomCategoryDisponibilityId;

    public RoomCategoryDisponibilityRoomPK(Integer roomId, Long roomCategoryDisponibilityId) {
        this.roomId = roomId;
        this.roomCategoryDisponibilityId = roomCategoryDisponibilityId;
    }
}
