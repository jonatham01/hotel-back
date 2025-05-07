package manager.mapper;

import manager.dto.FeeRequestDTO;
import manager.dto.FeeResponseDTO;
import manager.entity.Fee;
import org.springframework.stereotype.Component;

@Component
public class FeeMapper {

    public Fee toEntity(FeeRequestDTO dto){
        Fee fee = new Fee();
        fee.setPublicFee(dto.getPublicFee());
        fee.setDate(dto.getDate());
        fee.setIncrementRate(dto.getIncrementRate());
        fee.setRoomCategoryId(dto.getRoomCategoryId());
        return fee;
    }
    public FeeResponseDTO toDto(Fee entity){
        return  FeeResponseDTO.builder()
                .publicFee(entity.getPublicFee())
                .date(entity.getDate())
                .incrementRate(entity.getIncrementRate())
                .roomCategoryId(entity.getRoomCategoryId())
                .build();
    }
}
