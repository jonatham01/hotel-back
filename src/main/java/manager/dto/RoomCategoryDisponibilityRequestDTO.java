package manager.dto;

import lombok.Data;
import manager.entity.RoomCategoryDisponibility;
import manager.entity.RoomCategoryDisponibilityRoomPK;

import java.math.BigInteger;

@Data
public class RoomCategoryDisponibilityRequestDTO {
    private RoomCategoryDisponibilityRoomPK id;
    private Integer roomId;

//    public RoomCategoryDisponibility toEntity() {
//        RoomCategoryDisponibility entity = new RoomCategoryDisponibility();
//        RoomCategoryDisponibilityRoomPK pk= new RoomCategoryDisponibilityRoomPK();
//        pk.
//    }
}
