package manager.entity.disabled;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.Payment;

import java.util.List;

@Entity
@Table(name = "payment_bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBill {

    @Id
    @Column(name = "bill_number")
    private String billNumber;
    @Column(name = "bill_photo_url")
    private String billPhotoUrl;
    @Column(name = "bill_tech_supplier")
    private String billTechSupplier;


    @ManyToOne
    @JoinColumn(name="bill_payment_id")
    private Payment billPayment;

    @OneToMany(mappedBy = "paymentNoteBill")
    List<PaymentCreditNote> billCreditNotes;
}
