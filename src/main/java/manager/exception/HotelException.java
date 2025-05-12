package manager.exception;

import manager.entity.Hotel;
import manager.security.ApiError;
import manager.security.ApiUnProcessableEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public class HotelException {

    public static void validate(Hotel request) throws ApiUnProcessableEntity {
        if (request.getHotelCountry() == null || request.getHotelCountry().isEmpty()) {
            throw new ApiUnProcessableEntity("Country's hotel is required");
        }
        if (request.getHotelState() == null || request.getHotelState().isEmpty()) {
            throw new ApiUnProcessableEntity("State's hotel is required");
        }
        if (request.getHotelCity() == null || request.getHotelCity().isEmpty()) {
            throw new ApiUnProcessableEntity("City's hotel is required");
        }
        if (request.getHotelAddress() == null || request.getHotelAddress().isEmpty()) {
            throw new ApiUnProcessableEntity("Address's hotel is required");
        }
        if (request.getHotelName() == null || request.getHotelName().isEmpty()) {
            throw new ApiUnProcessableEntity("Name's hotel is required");
        }
    }

    public static void notFound(Integer id) throws RuntimeException{
        throw new RuntimeException("Hotel Not found with id: " + id);
    }
    public static void uncreated() throws RuntimeException{
        throw new RuntimeException("Hotel could not be created");
    }
    public static void notFind() throws RuntimeException{
        throw new RuntimeException("System could not show hotels");
    }
    public static void notUpdated() throws RuntimeException{
        throw new RuntimeException("System could not update the hotel");
    }
    public static void notDeleted() throws RuntimeException{
        throw new RuntimeException("System could not delete the hotel");
    }

}
