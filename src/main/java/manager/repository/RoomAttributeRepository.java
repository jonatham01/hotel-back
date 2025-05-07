package manager.repository;

import manager.entity.RoomAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomAttributeRepository extends JpaRepository<RoomAttribute, Integer> {
    Optional<RoomAttribute> findByRoomAttributeName(String roomAttributeName);
    boolean existsByRoomAttributeName(String name);
}
