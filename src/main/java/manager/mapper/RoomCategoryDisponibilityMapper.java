package manager.mapper;

import manager.dto.RCDisponibilityRequest;
import manager.entity.RoomCategoryDisponibility;

public class RoomCategoryDisponibilityMapper {

    public static RoomCategoryDisponibility mapDtoToRoomCategoryDisponibility(RCDisponibilityRequest dto){
        RoomCategoryDisponibility roomCategory = new RoomCategoryDisponibility();
        roomCategory.setCategoryId(dto.getCategoryId());
        roomCategory.setCategoryName(dto.getCategoryName());
        roomCategory.setDate(dto.getDate());
        roomCategory.setQuantity(dto.getQuantity());
        roomCategory.setOccupancy(dto.getOccupancy());
        roomCategory.setDisponibilityLevel(dto.getDisponibilityLevel());
        return roomCategory;
    }
}
