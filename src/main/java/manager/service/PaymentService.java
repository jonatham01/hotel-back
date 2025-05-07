package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.PaymentRequestDTO;
import manager.dto.PaymentResponseDTO;
import manager.entity.Payment;
import manager.mapper.PaymentMapper;
import manager.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponseDTO create(PaymentRequestDTO dto) {
        Payment payment = paymentMapper.toEntity(dto);
        return paymentMapper.toDTO(paymentRepository.save(payment));
    }

    public List<PaymentResponseDTO> getAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaymentResponseDTO getById(UUID id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    public PaymentResponseDTO update(UUID id, PaymentRequestDTO dto) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        Payment updated = paymentMapper.toEntity(dto);
        updated.setId(id);

        return paymentMapper.toDTO(paymentRepository.save(updated));
    }

    public void delete(UUID id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }
}
