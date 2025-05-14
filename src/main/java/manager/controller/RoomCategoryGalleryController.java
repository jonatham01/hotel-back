package manager.controller;

import manager.dto.GalleryResponseDTO;
import manager.entity.RoomCategoryGallery;
import manager.service.RoomCategoryGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class RoomCategoryGalleryController {

    private final RoomCategoryGalleryService service;

    @Autowired
    public RoomCategoryGalleryController(RoomCategoryGalleryService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @RequestParam String tittle,
            @RequestParam String description,
            @RequestParam Integer categoryId,
            @RequestPart MultipartFile image
    ) {
        try {
            GalleryResponseDTO gallery = service.save(tittle, description, image, categoryId);
            return ResponseEntity.ok(gallery);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving image");
        }
    }

    @GetMapping("{id}")
    public List<GalleryResponseDTO> getAll(@PathVariable Integer categoryId) {
        return service.getAll(categoryId);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(true);
    }
}
