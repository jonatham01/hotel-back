package manager.mapper;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomAttributeDto;
import manager.dto.RoomAttributeResponseDTO;
import manager.dto.RoomCategoryRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.RoomAttribute;
import manager.entity.RoomCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomCategoryMapper {
   private final GalleryMapper galleryMapper;
    public static RoomCategory dtoToEntity(RoomCategoryRequest roomCategory) {
        RoomCategory newEntity = new RoomCategory();
        newEntity.setRoomCategoryDescription(roomCategory.getRoomCategoryDescription());
        newEntity.setRoomCategoryName(roomCategory.getRoomCategoryName());
        newEntity.setRoomCategoryPrice(roomCategory.getRoomCategoryPrice());

        return newEntity;
    }

    public  RoomCategoryResponse entityToDto(RoomCategory roomCategory) {
        List<RoomAttributeResponseDTO> roomAttributeDto =  roomCategory.getRoomAttributes().stream()
                .map(RoomAttributeMapper::toDTO).toList();
        return RoomCategoryResponse.builder()
                .roomCategoryId(roomCategory.getRoomCategoryId())
                .roomCategoryName(roomCategory.getRoomCategoryName())
                .roomCategoryDescription(roomCategory.getRoomCategoryDescription())
                .roomCategoryPrice(roomCategory.getRoomCategoryPrice())

                //.hotel(roomCategory.getHotel())
                .roomAttributes(roomAttributeDto)
                .roomCategoryGalleries(
                        roomCategory.getRoomCategoryGalleries().stream().map(data-> galleryMapper.toDto(data))
                                .toList()
                )
                .build();
    }
}
