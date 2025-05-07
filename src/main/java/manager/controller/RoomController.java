package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomDto;
import manager.dto.RoomResponse;
import manager.entity.Room;
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

    @GetMapping()
    public ResponseEntity<List<RoomResponse>> getRoom() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
    @GetMapping("id/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
    @PostMapping()
    public ResponseEntity<Room> createRoom(RoomDto dto){
      return ResponseEntity.ok(roomService.createRoom(dto));
    }
    @PutMapping()
    public ResponseEntity<Room> updateRoom(RoomDto dto, int id){
        return ResponseEntity.ok(roomService.updateRoom(dto,id));
    }
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteRoom(int id){
        return ResponseEntity.ok(roomService.deleteRoomById(id));
    }
}
