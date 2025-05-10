package manager.exception;

import manager.entity.RoomCategory;
import manager.security.ApiUnProcessableEntity;

public class RoomCategoryException {

    public static void validate(RoomCategory roomCategory) throws ApiUnProcessableEntity {
        if (roomCategory.getRoomCategoryDescription() == null) throw new ApiUnProcessableEntity("Room category description is required");
        if (roomCategory.getRoomCategoryName() == null) throw new ApiUnProcessableEntity("Room category name is required");
        if (roomCategory.getRoomCategoryPrice()==null) throw new ApiUnProcessableEntity("Room category price is required");

    }
    public static void dontExist() throws ApiUnProcessableEntity{
        throw new ApiUnProcessableEntity("Room category does not exist in Database");
    }

    public static void exception(String message) throws RuntimeException {
        throw new RuntimeException(message);
    }
}
