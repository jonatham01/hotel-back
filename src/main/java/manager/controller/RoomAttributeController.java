package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomAttributeDto;
import manager.dto.RoomAttributeResponseDTO;
import manager.entity.RoomAttribute;
import manager.service.RoomAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("room-attribute")
@RequiredArgsConstructor
public class RoomAttributeController {
    private  final RoomAttributeService service ;

    @GetMapping
    public ResponseEntity<List<RoomAttributeResponseDTO>> getRoomAttribute() {
        return ResponseEntity.ok().body(service.getAll());
    }
    @GetMapping("id")
    public ResponseEntity<RoomAttributeResponseDTO> getRoomAttributeById(@RequestParam int id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createRoomAttribute(
            @RequestPart("data") RoomAttributeDto dto,
            @RequestPart("file") MultipartFile file
    ) {
        try {
            RoomAttributeResponseDTO response = service.create(dto, file);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Image upload failed");
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateRoomAttribute(@PathVariable int id,  @RequestPart("data") RoomAttributeDto dto,  @RequestPart("file") MultipartFile file) {
        RoomAttributeResponseDTO response = service.update(id, dto, file);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteRoomAttribute(@PathVariable int id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
