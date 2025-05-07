package manager.mapper;
import manager.dto.PaymentRequestDTO;
import manager.dto.PaymentResponseDTO;
import manager.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setPaymentTotalAmount(dto.getPaymentTotalAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentClientId(dto.getPaymentClientId());
        return payment;
    }

    public PaymentResponseDTO toDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .paymentTotalAmount(payment.getPaymentTotalAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentClientId(payment.getPaymentClientId())
                .build();
    }
}

