package manager.mapper;

import lombok.RequiredArgsConstructor;
import manager.dto.GalleryResponseDTO;
import manager.entity.RoomCategoryGallery;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GalleryMapper {

    public GalleryResponseDTO toDto(RoomCategoryGallery entity){
        return  GalleryResponseDTO.builder()
                .id(entity.getId())
                .tittle(entity.getTittle())
                .description(entity.getRoomGalleryDescription())
                .categoryId(entity.getRoomCategory().getRoomCategoryId())
                .categoryName(entity.getRoomCategory().getRoomCategoryName())
                .imageUrl(entity.getRoomGalleryImageUrl())
                .build();
    }
}
