package manager.controller;

import manager.dto.GuestRequestDTO;
import manager.dto.GuestResponseDTO;
import manager.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestResponseDTO> create(@RequestBody GuestRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.createGuest(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> getById(@PathVariable BigInteger id) {
        return ResponseEntity.ok(guestService.getGuestById(id));
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getAll() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> update(@PathVariable BigInteger id,
                                                   @RequestBody GuestRequestDTO dto) {
        return ResponseEntity.ok(guestService.updateGuest(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable BigInteger id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}

