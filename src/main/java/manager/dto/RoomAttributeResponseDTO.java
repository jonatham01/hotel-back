package manager.dto;

import lombok.Data;

@Data
public class RoomAttributeResponseDTO {
    private int roomAttributeId;
    private String roomAttributeName;
    private String roomAttributeDescription;
    private String roomAttributePhotoUrl;
    private Integer roomCategoryId;
}

