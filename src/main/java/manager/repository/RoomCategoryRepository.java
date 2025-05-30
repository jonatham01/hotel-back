package manager.repository;

import manager.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomCategoryRepository  extends JpaRepository<RoomCategory, Integer> {
    //List<RoomCategory> findByRoomCategoryHotelId(Integer id);
    List<RoomCategory> findByHotel_HotelId(Integer hotelId);
    Optional<RoomCategory> findByRoomCategoryName(String roomCategoryName);

    @Query("""
    SELECT r FROM RoomCategory r 
    WHERE r.hotel.hotelId = :hotelId
    AND (:roomCategoryName IS NULL OR r.roomCategoryName = :roomCategoryName)
    AND (:minPrice IS NULL OR r.roomCategoryPrice >= :minPrice)
    AND (:maxPrice IS NULL OR r.roomCategoryPrice <= :maxPrice)
""")
    List<RoomCategory> findRoomCategories(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("hotelId") Integer hotelId,
            @Param("roomCategoryName") String roomCategoryName
    );


//    @Query("SELECT r FROM RoomCategory r WHERE (r.roomCategoryPrice BETWEEN :minPrice AND :maxPrice AND r.hotel.hotelId = :hotelId) OR (:roomCategoryName IS NOT NULL AND r.roomCategoryName = :roomCategoryName) AND r.hotel.hotelId = :hotelId")
//    List<RoomCategory> findRoomCategories(
//            @Param("minPrice") double minPrice,
//            @Param("maxPrice") double maxPrice,
//            @Param("hotelId") Integer hotelId,
//            @Param("roomCategoryName") String roomCategoryName
//    );

}
