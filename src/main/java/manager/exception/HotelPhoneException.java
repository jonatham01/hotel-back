package manager.exception;

import manager.entity.HotelPhone;
import manager.security.ApiUnProcessableEntity;
import org.apache.logging.log4j.util.Strings;

public class HotelPhoneException {

    public static void validate(HotelPhone hotelPhone) throws ApiUnProcessableEntity {
        if(Strings.isEmpty(hotelPhone.getNumber()))throw new ApiUnProcessableEntity("Hotel Phone number is empty");

        if(hotelPhone.getHotelId()==null)throw new ApiUnProcessableEntity("Hotel Id is empty");
    }

    public static void dontExist() throws ApiUnProcessableEntity {
        throw new ApiUnProcessableEntity("Hotel Phone number does not exist");
    }
}
