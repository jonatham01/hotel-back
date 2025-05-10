package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

 @Data
  @Builder
public class PaymentTransactionResponseDTO {
    private int id;
    private double total;
    private String kind;
    private LocalDateTime dateTime;
    private UUID paymentId;
    private Long clientId;
}
