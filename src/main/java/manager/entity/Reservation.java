package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

//por cada habitacion se genera una reservacion
@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "reservation_start_date")
    private LocalDate reservationStartDate;
    @Column(name = "reservation_end_date")
    private LocalDate reservationEndDate;

    @Column(name = "reservation_total_value")
    private BigDecimal reservationTotalValue;

    @Column(name = "reservation_checkin_status")
    private String reservationCheckinStatus;
    //reserved,checkin,checkout, cancelled,
    @Column(name = "reservation_checkout_status")
    private String reservationCheckoutStatus;

    @Column(name = "reservation_paymentStatus")
    private String reservationPaymentStatus;

    //include any cost by guests?
//    @Column(name="reservation_guest_cost_validation")
//    private String reservationGuestCostValidation;
//
//    @Column(name = "reservation_total_guest_cost")
//    private BigDecimal reservationTotalGuestCost;


    @ManyToOne
    @JoinColumn(name = "reservation_paymentId",nullable = true)
    private Payment reservationPayment;

    @ManyToOne
    @JoinColumn(name = "reservation_hotel_id")
    private Hotel reservationHotel;

    @ManyToOne
    @JoinColumn(name = "reservation_room_id")
    private Room reservationRoom;

    @ManyToOne
    @JoinColumn(name = "reservation_client_id")
    private Client reservationClient;

//    @OneToMany(mappedBy = "reservation")
//    private List<RoomOccupancy> roomAvailabilities;

//    @OneToMany(mappedBy = "reservation")
//    private List<ReservationGuest> reservationGuests;


    //@Column(name = "reservation_payment_records")

    //lista de habitaciones con su valor
    //LISTADO DE HUESPEDES


}
