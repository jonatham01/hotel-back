package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ReservationRequestDTO {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private BigDecimal reservationTotalValue;
    private Integer reservationHotelId;
    private Integer reservationRoomCategoryId;

    private Integer reservationClientId;
}
