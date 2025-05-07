package manager.exception;

import manager.dto.RoomAttributeDto;
import manager.security.ApiUnProcessableEntity;
import org.apache.logging.log4j.util.Strings;

public class RoomAttributeException {

    public static void validateNewRoomAttribute(RoomAttributeDto room)  throws ApiUnProcessableEntity {
        if(Strings.isNotEmpty(room.getRoomAttributeName()))throw  new ApiUnProcessableEntity("Room attribute name cannot be empty");

        if(Strings.isNotEmpty(room.getRoomAttributeDescription()))throw  new ApiUnProcessableEntity("Room attribute description cannot be empty");

        //if(Strings.isNotEmpty(room.getRoomAttributePhotoUrl()))throw  new ApiUnProcessableEntity("Room attribute photo url cannot be empty");
    }
    public static void dontExist() throws ApiUnProcessableEntity{
        throw new ApiUnProcessableEntity("Room attribute does not exist in Database");
    }

    public static void exception(String message) throws RuntimeException {
        throw new RuntimeException(message);
    }

}
