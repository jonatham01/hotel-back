package manager.mapper;

import manager.dto.RoomDto;
import manager.dto.RoomResponse;
import manager.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public static Room toEntity(RoomDto dto) {
        Room room = new Room();
        room.setName(dto.getName());
        room.setDescription(dto.getDescription());
        room.setRoomCategoryId(dto.getRoomCategoryHotelId());
        room.setRoomCategoryHotelId(dto.getRoomCategoryHotelId());
        room.setRoomStatus(dto.getRoomStatus());
        return room;
    }

    public static RoomResponse toResponse(Room room) {
        return RoomResponse.builder()
                .roomId(room.getRoomId())
                .name(room.getName())
                .description(room.getDescription())
                .roomCategoryId(room.getRoomCategoryHotelId())
                .roomCategoryHotelId(room.getRoomCategoryHotelId())
                .availabilities(room.getAvailabilityRooms())
                .roomStatus(room.getRoomStatus())
                .build();
    }
}
