package manager.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private String name;
    private String description;
    private Integer roomCategoryId;
    private Integer roomCategoryHotelId;
    private String roomStatus;
}
