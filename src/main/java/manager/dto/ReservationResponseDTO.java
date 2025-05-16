package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ReservationResponseDTO {
    private Long reservationId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private BigDecimal reservationTotalValue;
    private String reservationCheckinStatus;
    private String reservationCheckoutStatus;
    private Integer reservationHotelId;
    private Integer reservationRoomId;
    private String reservationRoomName;
    private UUID reservationPaymentId;
    private Long reservationClientId;
    private String reservationClientName;
}
