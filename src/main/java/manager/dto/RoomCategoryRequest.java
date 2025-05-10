package manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCategoryRequest {
    private String roomCategoryName;
    private Double roomCategoryPrice;
    private String roomCategoryDescription;
    private Integer roomCategoryHotelId;
}
