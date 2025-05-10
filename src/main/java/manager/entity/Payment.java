package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//un payment tiene un listado de reservaciones de una habitacion
// primero se crea como pendiente, luego se registra como pagado.
@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="payment_id")
    private UUID id;

    @Column(name = "payment_total_amount")
    private BigDecimal paymentTotalAmount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "reservation_client_id")
    private Long paymentClientId;

    @OneToMany(mappedBy = "reservationPayment")
    List<Reservation> paymentReservations;

//    @OneToMany(mappedBy = "creditNotePayment")
//    List<PaymentCreditNote> paymentCreditNotes;
//
//    @OneToMany(mappedBy = "billPayment")
//    List<PaymentBill> paymentBills;

    @OneToMany(mappedBy = "payment")
    List<PaymentTransaction> paymentTransactions;

    //List<RoomCategory> listRoomCategoriesPayment;
}
