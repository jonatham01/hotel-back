package manager.mapper;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomDto;
import manager.dto.RoomResponse;
import manager.entity.Hotel;
import manager.entity.Room;
import manager.entity.RoomCategory;
import manager.repository.HotelRepository;
import manager.repository.RoomCategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMapper {
    private final RoomCategoryRepository roomCategoryRepository;
    private final HotelRepository hotelRepository;
    public  Room toEntity(RoomDto dto) {
        RoomCategory roomCategory= roomCategoryRepository.findById(dto.getRoomCategoryId())
                .orElseThrow(()-> new RuntimeException("roomCategory not found"));
        Hotel hotel = hotelRepository.findById(dto.getRoomCategoryHotelId())
                .orElseThrow(()-> new RuntimeException("Hotel not found"));
        Room room = new Room();
        room.setName(dto.getName());
        room.setDescription(dto.getDescription());
        room.setRoomCategory(roomCategory);
        room.setHotel(hotel);
        room.setRoomStatus(dto.getRoomStatus());
        return room;
    }

    public RoomResponse toResponse(Room room) {
        return RoomResponse.builder()
                .roomId(room.getRoomId())
                .name(room.getName())
                .description(room.getDescription())
                .roomCategoryId(room.getRoomCategory().getRoomCategoryId())
                .roomCategoryHotelId(room.getHotel().getHotelId())
                //.availabilities(room.getAvailabilityRooms())
                .roomStatus(room.getRoomStatus())
                .categoryName(room.getRoomCategory().getRoomCategoryName())
                .build();
    }
}