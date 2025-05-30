package manager.repository;

import manager.entity.RoomCategoryDisponibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RoomCategoryDisponibilityRepository extends JpaRepository<RoomCategoryDisponibility, Long> {
//    boolean existsByRoomCategoryIdAAndDate(int id, LocalDate date);
    //Optional<RoomCategoryDisponibility> findByRoomCategoryIdAAndDate(int id, LocalDate date);
    //Optional<RoomCategoryDisponibility> findByCategoryIdAAndDate(int id, LocalDate date);
    Optional<RoomCategoryDisponibility> findByCategoryIdAndDate(int id, LocalDate date);


}
