package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PaymentResponseDTO {
    private UUID id;
    private BigDecimal paymentTotalAmount;
    private LocalDateTime paymentDate;
    private Long paymentClientId;
}
