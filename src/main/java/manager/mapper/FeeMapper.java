package manager.mapper;

import lombok.RequiredArgsConstructor;
import manager.dto.FeeRequestDTO;
import manager.dto.FeeResponseDTO;
import manager.entity.Fee;
import manager.entity.RoomCategory;
import manager.repository.FeeRepository;
import manager.repository.RoomCategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeeMapper {
    private final RoomCategoryRepository repository;

    public Fee toEntity(FeeRequestDTO dto){
        RoomCategory roomCategory = repository.findById(dto.getRoomCategoryId()).orElseThrow(
                () -> new RuntimeException("RoomCategory not found")
        );
        Fee fee = new Fee();
        fee.setPublicFee(dto.getPublicFee());
        fee.setDate(dto.getDate());
        fee.setIncrementRate(dto.getIncrementRate());
        fee.setRoomCategory(roomCategory);
        return fee;
    }
    public static FeeResponseDTO toDto(Fee entity){
        return  FeeResponseDTO.builder()
                .publicFee(entity.getPublicFee())
                .date(entity.getDate())
                .incrementRate(entity.getIncrementRate())
                .roomCategoryId(entity.getRoomCategory().getRoomCategoryId())
                .build();
    }
}
