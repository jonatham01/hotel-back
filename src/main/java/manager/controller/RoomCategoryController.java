package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomCategoryRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.RoomCategory;
import manager.service.RoomCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("roomcategories")
@RequiredArgsConstructor
public class RoomCategoryController {
    private final RoomCategoryService roomCategoryService;
    @GetMapping()
    public ResponseEntity<List<RoomCategoryResponse>> findAll() {
        return ResponseEntity.ok().body(roomCategoryService.findAll());
    }
    @GetMapping("id/{id}")
    public ResponseEntity<RoomCategoryResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(roomCategoryService.findById(id));
    }
    @GetMapping("filter")
    public ResponseEntity<List<RoomCategoryResponse>> findByFilter(@RequestParam String name, @RequestParam double min, @RequestParam double max, @RequestParam Integer hotelId ){
            return ResponseEntity.ok().body(roomCategoryService.findByParameters(name, min, max, hotelId));
    }
    @GetMapping("available")
    public List<RoomCategoryResponse> findAvailableRooms(LocalDate date) {
        return roomCategoryService.findByDisponibility(date);
    }
    @PostMapping()
    public ResponseEntity<RoomCategoryResponse> create(@RequestBody RoomCategoryRequest roomCategoryRequest) {
        return ResponseEntity.ok().body(roomCategoryService.createRoomCategory(roomCategoryRequest));
    }
    @PutMapping()
    public ResponseEntity<RoomCategoryResponse> update(@RequestBody RoomCategory roomCategory) {
        return ResponseEntity.ok().body(roomCategoryService.update(roomCategory));
    }
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        roomCategoryService.deleteById(id);
        return ResponseEntity.ok().body("Room category have been deleted");
    }



}
