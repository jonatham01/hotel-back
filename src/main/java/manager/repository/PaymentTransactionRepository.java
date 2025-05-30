package manager.repository;

import manager.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Integer> {
//     public List<PaymentTransaction> findAllByPayment_Id(UUID paymentId);
//    List<PaymentTransaction> findAllByPaymentCashTotalBetween(BigDecimal min, BigDecimal max);
}
