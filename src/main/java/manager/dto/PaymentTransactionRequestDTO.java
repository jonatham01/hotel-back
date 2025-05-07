package manager.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentTransactionRequestDTO {
    private double total;
    private String kind;
    private LocalDateTime dateTime;
    private UUID paymentId;
    private Integer clientId;
}
