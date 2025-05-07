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
    List<RoomCategory> findByRoomCategoryHotelId(Integer id);
    Optional<RoomCategory> findByRoomCategoryName(String roomCategoryName);

    @Query("SELECT r FROM RoomCategory r WHERE (r.roomCategoryPrice BETWEEN :minPrice AND :maxPrice AND r.roomCategoryHotelId = :hotelId) OR (:roomCategoryName IS NOT NULL AND r.roomCategoryName = :roomCategoryName) AND r.roomCategoryHotelId = :hotelId")
    List<RoomCategory> findRoomCategories(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("hotelId") Integer hotelId,
            @Param("roomCategoryName") String roomCategoryName
    );
}
