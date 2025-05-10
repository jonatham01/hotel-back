package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "fee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Long roomCategoryFeeId;

    @Column(name = "fee_public_value")
    private Double publicFee;

    //se genera una tarifa para cada dia
    @Column(name = "fee_date")
    private LocalDate date;

    @Column(name = "fee_increment_rate")
    private Short incrementRate;

    @ManyToOne()
    @JoinColumn(name = "fee_category_id")
    private RoomCategory roomCategory;


}
