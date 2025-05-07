package manager.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class FeeRequestDTO {
    private Double publicFee;
    private LocalDate date;
    private Short incrementRate;
    private Integer roomCategoryId;
}
