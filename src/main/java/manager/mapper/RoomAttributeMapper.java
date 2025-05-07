package manager.mapper;


import manager.dto.RoomAttributeDto;
import manager.dto.RoomAttributeResponseDTO;
import manager.entity.RoomAttribute;

public class RoomAttributeMapper {

    public static RoomAttribute dtoToEntity(RoomAttributeDto dto, String imageUrl) {
        RoomAttribute entity = new RoomAttribute();
        entity.setRoomAttributeName(dto.getRoomAttributeName());
        entity.setRoomAttributeDescription(dto.getRoomAttributeDescription());
        entity.setRoomCategoryId(dto.getRoomCategoryId());
        entity.setRoomAttributePhotoUrl(imageUrl);
        return entity;
    }
    public static RoomAttributeResponseDTO toDTO(RoomAttribute entity) {
        RoomAttributeResponseDTO dto = new RoomAttributeResponseDTO();
        dto.setRoomAttributeId(entity.getRoomAttributeId());
        dto.setRoomAttributeName(entity.getRoomAttributeName());
        dto.setRoomAttributeDescription(entity.getRoomAttributeDescription());
        dto.setRoomAttributePhotoUrl(entity.getRoomAttributePhotoUrl());
        dto.setRoomCategoryId(entity.getRoomCategoryId());
        return dto;
    }
}
