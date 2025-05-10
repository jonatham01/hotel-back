package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomDto;
import manager.dto.RoomResponse;
import manager.entity.Room;
import manager.mapper.RoomMapper;
import manager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping()
    public ResponseEntity<List<RoomResponse>> getRoom() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
    @GetMapping("id/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
    @PostMapping()
    public ResponseEntity<RoomResponse> createRoom(RoomDto dto){
        Room room = roomService.createRoom(dto);
        return ResponseEntity.ok(roomMapper.toResponse(room));
    }
    @PutMapping()
    public ResponseEntity<RoomResponse> updateRoom(RoomDto dto, int id){
        Room room = roomService.updateRoom(dto, id);
        return ResponseEntity.ok(roomMapper.toResponse(room));
    }
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteRoom(int id){
        return ResponseEntity.ok(roomService.deleteRoomById(id));
    }
}
