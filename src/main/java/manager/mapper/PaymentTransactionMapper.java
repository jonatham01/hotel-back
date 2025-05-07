package manager.mapper;

import manager.dto.PaymentTransactionRequestDTO;
import manager.dto.PaymentTransactionResponseDTO;
import manager.entity.PaymentTransaction;

public class PaymentTransactionMapper {

    public static PaymentTransaction mapToEntity(PaymentTransactionRequestDTO dto) {
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setPaymentCashTotal(dto.getTotal());
        paymentTransaction.setPaymentCashKind(dto.getKind());
        paymentTransaction.setPaymentCashDateTime(dto.getDateTime());
        paymentTransaction.setPaymentCashPaymentId(dto.getPaymentId());
        return paymentTransaction;
    }
    public static PaymentTransactionResponseDTO mapToDTO(PaymentTransaction entity) {
        return PaymentTransactionResponseDTO.builder()
                .total(entity.getPaymentCashTotal())
                .kind(entity.getPaymentCashKind())
                .dateTime(entity.getPaymentCashDateTime())
                .paymentId(entity.getPaymentCashPaymentId())
                .build();
    }
}
