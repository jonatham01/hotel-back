package manager.mapper;

import manager.dto.RoomAttributeDto;
import manager.dto.RoomAttributeResponseDTO;
import manager.dto.RoomCategoryRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.RoomAttribute;
import manager.entity.RoomCategory;

import java.util.List;

public class RoomCategoryMapper {

    public static RoomCategory dtoToEntity(RoomCategoryRequest roomCategory) {
        RoomCategory newEntity = new RoomCategory();
        newEntity.setRoomCategoryDescription(roomCategory.getRoomCategoryDescription());
        newEntity.setRoomCategoryName(roomCategory.getRoomCategoryName());
        newEntity.setRoomCategoryPrice(roomCategory.getRoomCategoryPrice());

        return newEntity;
    }

    public static RoomCategoryResponse entityToDto(RoomCategory roomCategory) {
        List<RoomAttributeResponseDTO> roomAttributeDto =  roomCategory.getRoomAttributes().stream()
                .map(RoomAttributeMapper::toDTO).toList();
        return RoomCategoryResponse.builder()
                .roomCategoryName(roomCategory.getRoomCategoryName())
                .roomCategoryDescription(roomCategory.getRoomCategoryDescription())
                .roomCategoryPrice(roomCategory.getRoomCategoryPrice())

                .hotel(roomCategory.getHotel())
                .roomAttributes(roomAttributeDto)
                .roomCategoryGalleries(roomCategory.getRoomCategoryGalleries())
                .build();
    }
}
