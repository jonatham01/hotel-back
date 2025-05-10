package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomDto;
import manager.dto.RoomResponse;
import manager.entity.Room;
import manager.mapper.RoomMapper;
import manager.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import  manager.mapper.RoomMapper;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomResponse> getAllRooms() {
        try {
            return roomRepository.findAll().stream()
                    .map(roomMapper::toResponse).toList();
        }catch (Exception e) {
            throw new RuntimeException("System couldn't get all the rooms");
        }
    }
    public RoomResponse getRoomById(int id) {
        try{
            return roomMapper.toResponse(roomRepository.findById(id).get());
        }catch (Exception e) {
            throw new RuntimeException("System couldn't get room by id");
        }
    }

    public Room createRoom(RoomDto room) {
        try {
            return roomRepository.save(roomMapper.toEntity(room) );
        }
        catch (Exception e) {
            throw new RuntimeException("System couldn't create room");
        }
    }
    public Room updateRoom(RoomDto dto, int id) {
        try {
            Room room= roomMapper.toEntity(dto);
            room.setRoomId(id);
            return roomRepository.save(room);
        }
        catch (Exception e) {
            throw new RuntimeException("System couldn't update room");
        }
    }
    public boolean deleteRoomById(int id) {
        try {
            roomRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException("System couldn't delete room by id");
        }
    }

}
