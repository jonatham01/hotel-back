package manager.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class HotelPhoneRequest {
    private String number;
    private Integer hotelId;
}
