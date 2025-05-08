package manager.mapper;

import lombok.AllArgsConstructor;
import manager.dto.PaymentTransactionRequestDTO;
import manager.dto.PaymentTransactionResponseDTO;
import manager.entity.Payment;
import manager.entity.PaymentTransaction;
import manager.repository.PaymentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PaymentTransactionMapper {
    private final PaymentRepository paymentRepository ;

    public  PaymentTransaction mapToEntity(PaymentTransactionRequestDTO dto) {
        Payment payment = paymentRepository.findById(dto.getPaymentId()).orElseThrow(
                () -> new IllegalArgumentException("Invalid payment id: " + dto.getPaymentId())
        );
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setPaymentCashTotal(dto.getTotal());
        paymentTransaction.setPaymentCashKind(dto.getKind());
        paymentTransaction.setPaymentCashDateTime(dto.getDateTime());
        paymentTransaction.setPayment(payment);
        return paymentTransaction;
    }
    public static PaymentTransactionResponseDTO mapToDTO(PaymentTransaction entity) {
        return PaymentTransactionResponseDTO.builder()
                .total(entity.getPaymentCashTotal())
                .kind(entity.getPaymentCashKind())
                .dateTime(entity.getPaymentCashDateTime())
                .paymentId(entity.getPayment().getId())
                .id(entity.getPaymentCashId())
                .build();
    }
}
