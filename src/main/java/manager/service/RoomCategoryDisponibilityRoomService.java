package manager.service;

import lombok.RequiredArgsConstructor;
import manager.entity.RoomCategoryDisponibilityRoom;
import manager.entity.RoomCategoryDisponibilityRoomPK;
import manager.repository.RoomCategoryDisponibilityRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomCategoryDisponibilityRoomService {

    private final RoomCategoryDisponibilityRoomRepository repository;

    public List<RoomCategoryDisponibilityRoom> findAll() {
        return repository.findAll();
    }

    public RoomCategoryDisponibilityRoom save(RoomCategoryDisponibilityRoom entity) {
        return repository.save(entity);
    }

    public void deleteById(RoomCategoryDisponibilityRoomPK id) {
        repository.deleteById(id);
    }

    public Optional<RoomCategoryDisponibilityRoom> findById(RoomCategoryDisponibilityRoomPK id) {
        return repository.findById(id);
    }

    public List<RoomCategoryDisponibilityRoom> findByRoomId(Integer roomId) {
        return repository.findByRoom_Id(roomId);
    }

    public List<RoomCategoryDisponibilityRoom> findByDisponibilityId(Integer disponibilityId) {
        return repository.findByDisponibility_Id(disponibilityId);
    }
}
