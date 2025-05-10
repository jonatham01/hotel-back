package manager.entity.disabled;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manager.entity.Hotel;
import manager.entity.RoomCategory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
//@Table(name = "room_category_offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private BigInteger offerId;

    @Column(name = "offer_days_quantity")
    private int offerDaysQuantity;
    @Column(name = "offer_description")
    private String offerDescription;
    @Column(name = "offer_price")
    private BigDecimal offerPrice;

    @Column(name = "offer_start_reservation")
    private LocalDate offerStartDateReservation;
    @Column(name = "offer_end_reservation")
    private LocalDate offerEndDateReservation;

    @Column(name = "offer_start_availabilty")
    private LocalDate offerStartDateAvailability;
    @Column(name = "offer_end_availability")
    private LocalDate offerEndDateAvailability;



//    @ManyToOne()
//    @JoinColumn(name = "offer_category_id")
//    private RoomCategory roomCategory;
//
//    @ManyToOne()
//    @JoinColumn(name = "offer_hotel_id")
//    private Hotel hotel;


}
