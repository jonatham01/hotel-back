package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "room_category_fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private BigInteger roomCategoryFeeId;

    @Column(name = "fee_public_value")
    private Double publicFee;

    //se genera una tarifa para cada dia
    @Column(name = "fee_date")
    private LocalDate date;

    @Column(name = "fee_increment_rate")
    private Short incrementRate;

    @Column(name = "fee_category_id")
    private Integer roomCategoryId;

    @ManyToOne()
    @JoinColumn(name = "fee_category_id")
    private RoomCategory roomCategory;


}
