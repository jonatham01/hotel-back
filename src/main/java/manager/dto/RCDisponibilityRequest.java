package manager.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RCDisponibilityRequest {
    private Integer categoryId;
    private String categoryName;
    private LocalDate date;
    private Integer quantity;
    private Integer occupancy;
    private Integer disponibilityLevel;
}
