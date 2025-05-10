package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.entity.RoomCategoryDisponibilityRoom;
import manager.entity.RoomCategoryDisponibilityRoomPK;
import manager.service.RoomCategoryDisponibilityRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/rcdr")
@RequiredArgsConstructor
public class RoomCategoryDisponibilityRoomController {

    private final RoomCategoryDisponibilityRoomService service;

    @GetMapping
    public List<RoomCategoryDisponibilityRoom> getAll() {
        return service.findAll();
    }

    @GetMapping("/by-id")
    public ResponseEntity<RoomCategoryDisponibilityRoom> getById(
            @RequestParam Integer roomId,
            @RequestParam Long disponibilityId
    ) {
        RoomCategoryDisponibilityRoomPK id = new RoomCategoryDisponibilityRoomPK(roomId,disponibilityId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoomCategoryDisponibilityRoom create(@RequestBody RoomCategoryDisponibilityRoom entity) {
        return service.save(entity);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam Integer roomId,
            @RequestParam Long disponibilityId
    ) {
        RoomCategoryDisponibilityRoomPK id = new RoomCategoryDisponibilityRoomPK(roomId,disponibilityId);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-room")
    public List<RoomCategoryDisponibilityRoom> getByRoom(@RequestParam Integer roomId) {
        return service.findByRoomId(roomId);
    }

    @GetMapping("/by-disponibility")
    public List<RoomCategoryDisponibilityRoom> getByDisponibility(@RequestParam Integer disponibilityId) {
        return service.findByDisponibilityId(disponibilityId);
    }
}

