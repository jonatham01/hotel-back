package manager.entity.disabled;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.Payment;

@Entity
@Table(name = "payment_credit_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreditNote {

    @Id
    @Column(name = "credit_note_id")
    private String creditNoteId;
    @Column(name = "credit_note_txt")
    private String CreditNoteDescription;
//
//
//    @ManyToOne
//    @JoinColumn(name="credit_note_payment_id")
//    private Payment creditNotePayment;

//    @ManyToOne
//    @JoinColumn(name="credit_note_bill_id")
//    private PaymentBill paymentNoteBill;
}
