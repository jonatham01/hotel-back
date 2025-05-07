package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.math.BigInteger;

@Data
@Builder
public class PaymentRequestDTO {
    private BigDecimal paymentTotalAmount;
    private LocalDateTime paymentDate;
    private BigInteger paymentClientId;
}

