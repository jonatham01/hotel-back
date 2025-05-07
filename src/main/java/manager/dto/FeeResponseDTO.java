package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
public class FeeResponseDTO {
    private BigInteger roomCategoryFeeId;
    private Double publicFee;
    private LocalDate date;
    private Short incrementRate;
    private Integer roomCategoryId;
}
