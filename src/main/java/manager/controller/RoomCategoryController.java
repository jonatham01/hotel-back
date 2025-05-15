package manager.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manager.dto.RoomCategoryRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.RoomCategory;
import manager.service.RoomCategoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("room-categories")
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
    @GetMapping("available/{year}/{month}/{day}")
    public List<RoomCategoryResponse> availableRooms(@PathVariable String year, @PathVariable String month, @PathVariable String day, HttpServletRequest request) {
        System.out.println(">> URI Recibida: " + request.getRequestURI());
        System.out.println(">> Path: /room-categories/available/" + year + "/" + month + "/" + day);

        try {
            LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day);
            return roomCategoryService.findByDisponibility(localDate);
        }catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format");
        }
    }

    @GetMapping("filter")
    public ResponseEntity<List<RoomCategoryResponse>> findByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max,
            @RequestParam Integer hotelId) {
        //modificar

            return ResponseEntity.ok().body(roomCategoryService.findByParameters(name, min, max, hotelId));
    }

    @PostMapping()
    public ResponseEntity<RoomCategoryResponse> create(@RequestBody RoomCategoryRequest roomCategoryRequest) {
        return ResponseEntity.ok().body(roomCategoryService.createRoomCategory(roomCategoryRequest));
    }
    @PutMapping("update/{id}")
    public ResponseEntity<RoomCategoryResponse> update(@RequestBody RoomCategoryRequest roomCategory,@PathVariable Integer id) {
        return ResponseEntity.ok().body(roomCategoryService.update(roomCategory,id));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        roomCategoryService.deleteById(id);
        return ResponseEntity.ok().body("Room category have been deleted");
    }



}
