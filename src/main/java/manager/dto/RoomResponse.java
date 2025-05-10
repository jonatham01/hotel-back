package manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponse {
    private Integer roomId;
    private String name;
    private String description;
    private Integer roomCategoryId;
    private Integer roomCategoryHotelId;
    private String roomStatus;
    //private List<RoomOccupancy> availabilities;
}
