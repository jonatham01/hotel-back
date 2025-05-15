package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ReservationRequestDTO {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private BigDecimal reservationTotalValue;
    private Integer reservationHotelId;
    private Integer reservationRoomCategoryId;
    private Long reservationClientId;
    private UUID paymentId;
}
