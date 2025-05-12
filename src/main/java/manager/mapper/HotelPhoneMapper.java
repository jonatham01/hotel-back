package manager.mapper;

import manager.dto.HotelPhoneResponse;
import manager.entity.HotelPhone;

public class HotelPhoneMapper {

    public static HotelPhoneResponse toHotelPhoneResponse(HotelPhone hotelPhone) {
        String hotelName = (hotelPhone.getHotel() != null) ? hotelPhone.getHotel().getHotelName() : "Unknown Hotel";
        return new HotelPhoneResponse(
                hotelPhone.getId(),
                hotelPhone.getNumber(),
                hotelName
        );
    }
}
