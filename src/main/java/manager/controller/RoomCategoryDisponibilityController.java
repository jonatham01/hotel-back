package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.RCDisponibilityRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.RoomCategoryDisponibility;
import manager.exception.RCDisponibilityException;
import manager.service.RoomCategoryDisponibilityService;
import manager.service.RoomCategoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "disponibilities")
@RequiredArgsConstructor
public class RoomCategoryDisponibilityController {
    private final RoomCategoryDisponibilityService service;

    @PostMapping()
    public ResponseEntity<RoomCategoryDisponibility> createRoomCategoryDisponibility(RCDisponibilityRequest dto) throws RCDisponibilityException {
        return ResponseEntity.ok(service.createDisponibility(dto));
    }
    @GetMapping
    public ResponseEntity<List<RoomCategoryDisponibility>> getRoomCategoryDisponibilities() throws RCDisponibilityException {
        return ResponseEntity.ok(service.findAllDisponibility());
    }
    @GetMapping(path= "id/{id}")
    public ResponseEntity<RoomCategoryDisponibility> getRoomCategoryDisponibility(@PathVariable BigInteger disponibilityId) throws RCDisponibilityException {
        return ResponseEntity.ok(service.findDisponibilityById(disponibilityId).get());
    }
    @GetMapping(path = "/filter/{roomCategoryId}/{date}", name = "filter")
    public ResponseEntity<RoomCategoryDisponibility> getRoomCategoryDisponibilityByCategory(@PathVariable int roomCategoryId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws RCDisponibilityException {
        return ResponseEntity.ok(service.findDisponibilityByRoomCategory(roomCategoryId,date).get());
    }
    @PutMapping("update/{id}")
    public ResponseEntity<RoomCategoryDisponibility> modify(@RequestBody RCDisponibilityRequest dto, @PathVariable BigInteger id) throws RCDisponibilityException {
        return ResponseEntity.ok(service.update(dto,id));
    }


}
