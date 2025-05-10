package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.FeeRequestDTO;
import manager.dto.FeeResponseDTO;
import manager.service.FeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fees")
@RequiredArgsConstructor
public class FeeController {
    private final FeeService feeService;

    @GetMapping
    public ResponseEntity<List<FeeResponseDTO>> getFees() {
        return ResponseEntity.ok(feeService.findAll());
    }
    @GetMapping(name = "id")
    public ResponseEntity<FeeResponseDTO> getFee(@RequestParam Long id) {
        return ResponseEntity.ok(feeService.findById(id));
    }

    @GetMapping(name = "category")
    public ResponseEntity<List<FeeResponseDTO>> getFeeByRoomCategoryId(@RequestParam Integer idCategory) {
        List<FeeResponseDTO> responseDTOS= feeService.findByRoomCategoryId(idCategory);
        return ResponseEntity.ok().body(responseDTOS);
    }
    @PostMapping
    public ResponseEntity<FeeResponseDTO> createFee(@RequestBody FeeRequestDTO feeRequestDTO) {
        FeeResponseDTO fee = feeService.save(feeRequestDTO);
        return ResponseEntity.ok(fee);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<FeeResponseDTO> updateFee(@RequestBody FeeRequestDTO feeRequestDTO, @PathVariable Long id) {
        FeeResponseDTO fee= feeService.update(feeRequestDTO, id);
        return ResponseEntity.ok(fee);
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteFee(@PathVariable Long id) {
        feeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
