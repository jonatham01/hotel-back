package manager.repository;

import manager.entity.RoomCategoryDisponibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RoomCategoryDisponibilityRepository extends JpaRepository<RoomCategoryDisponibility, BigInteger> {
    boolean existsByRoomCategoryIdAAndDate(int id, LocalDate date);
    Optional<RoomCategoryDisponibility> findByRoomCategoryIdAAndDate(int id, LocalDate date);
}
