package manager.mapper;

import manager.dto.HotelPhoneResponse;
import manager.dto.HotelRequest;
import manager.dto.HotelResponse;
import manager.entity.Hotel;
import org.springframework.stereotype.Component;

public class HotelMapper {

    public static Hotel newHotelToHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelRequest.getName());
        hotel.setHotelCountry(hotelRequest.getCountry());
        hotel.setHotelCity(hotelRequest.getCity());
        hotel.setHotelState(hotelRequest.getState());
        hotel.setHotelAddress(hotelRequest.getAddress());
        return hotel;
    }

    public static HotelResponse hotelToHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getHotelId())
                .name(hotel.getHotelName())
                .address(hotel.getHotelAddress())
                .city(hotel.getHotelCity())
                .state(hotel.getHotelState())
                .country(hotel.getHotelCountry())
                .hotelPhones(
                        hotel.getHotelPhones().stream()
                                .map(hotelPhone -> {
                                    HotelPhoneResponse response = new HotelPhoneResponse();
                                    response.setHotelNumber(hotelPhone.getNumber()); // suponiendo que setHotelNumber existe
                                    return response;
                                })
                                .toList()
                )
                .build();
    }


}
