package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_cash")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_transaction_id")
    private int paymentCashId;

    @Column(name = "payment_transaction_total")
    private double paymentCashTotal;

    //tarjeta, efectivo, credito
    @Column(name = "payment_transaction_kind")
    private String paymentCashKind;


    @Column(name = "payment_transaction_datetime")
    private LocalDateTime paymentCashDateTime;

    @ManyToOne()
    @JoinColumn(name = "payment_transaction_payment_id")
    private Payment payment;
}
