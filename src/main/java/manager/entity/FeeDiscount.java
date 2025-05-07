package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Entity
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="discount_id")
    private BigInteger id;
    @Column(name="discount_rate")
    private int rate;
    @Column(name="discount_amount")
    private int amount;


    @ManyToOne()
    @JoinColumn(name="discount_fee_id")
    private Fee fee;

}
