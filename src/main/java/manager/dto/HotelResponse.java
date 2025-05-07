package manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.HotelPhone;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Integer id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private List<HotelPhoneResponse> hotelPhones;
}
