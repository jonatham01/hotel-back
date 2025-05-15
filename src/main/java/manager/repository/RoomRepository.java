package manager.repository;

import manager.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository  extends JpaRepository<Room, Integer> {
    List<Room> findAllByRoomStatus(String status);
    List<Room> findByRoomStatusAndRoomCategoryRoomCategoryId(String roomStatus, Integer roomCategoryId);

}
