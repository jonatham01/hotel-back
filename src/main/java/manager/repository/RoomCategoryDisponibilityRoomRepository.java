package manager.repository;

import manager.entity.RoomCategoryDisponibilityRoom;
import manager.entity.RoomCategoryDisponibilityRoomPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomCategoryDisponibilityRoomRepository extends JpaRepository<RoomCategoryDisponibilityRoom, RoomCategoryDisponibilityRoomPK> {
    List<RoomCategoryDisponibilityRoom> findByRoom_Id(Long roomId);
    List<RoomCategoryDisponibilityRoom> findByDisponibility_Id(Integer disponibilityId);
}

