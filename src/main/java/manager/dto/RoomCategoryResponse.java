package manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.Hotel;
import manager.entity.RoomAttribute;
import manager.entity.RoomCategoryGallery;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCategoryResponse {
    private Integer roomCategoryId;
    private String roomCategoryName;
    private Double roomCategoryPrice;
    private String roomCategoryDescription;
    private BigInteger roomCategoryGallery;
    private Hotel hotel;
    private List<RoomAttributeResponseDTO> roomAttributes;
    private List<RoomCategoryGallery> roomCategoryGalleries;
}
