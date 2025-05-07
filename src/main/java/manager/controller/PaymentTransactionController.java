package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.PaymentTransactionRequestDTO;
import manager.dto.PaymentTransactionResponseDTO;
import manager.service.PaymentTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payment-transactions")
@RequiredArgsConstructor
public class PaymentTransactionController {
    private final PaymentTransactionService paymentTransactionService;

    @PostMapping
    public ResponseEntity<PaymentTransactionResponseDTO> create(@RequestBody PaymentTransactionRequestDTO dto) {
        PaymentTransactionResponseDTO response = paymentTransactionService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTransactionResponseDTO> getById(@PathVariable int id) {
        PaymentTransactionResponseDTO response = paymentTransactionService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentTransactionResponseDTO>> getAll() {
        List<PaymentTransactionResponseDTO> responseList = paymentTransactionService.findAll();
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentTransactionResponseDTO> update(@PathVariable int id, @RequestBody PaymentTransactionRequestDTO dto) {
        PaymentTransactionResponseDTO response = paymentTransactionService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            paymentTransactionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

