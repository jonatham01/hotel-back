package manager.mapper;

import manager.dto.HotelPhoneResponse;
import manager.entity.HotelPhone;

public class HotelPhoneMapper {

    public static HotelPhoneResponse toHotelPhoneResponse(HotelPhone hotelPhone) {
        return new HotelPhoneResponse(
                hotelPhone.getNumber(),
                hotelPhone.getHotel().getHotelName()
        );
    }
}
