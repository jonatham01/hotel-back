package manager.controller;


import lombok.RequiredArgsConstructor;
import manager.dto.PaymentRequestDTO;
import manager.dto.PaymentResponseDTO;
import manager.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.status(201).body(paymentService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PaymentResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentResponseDTO> update(@PathVariable UUID id,
                                                     @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(paymentService.update(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

