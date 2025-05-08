package manager.entity.disabled;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation_guest")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationGuest {

    @EmbeddedId
    private ReservationGuestPK reservationGuestPK;

    // (por ejemplo, huésped principal, acompañante, etc.),
    @Column(name="reservation_guest_role")
    private String guestRole;

    @Column(name="reservation_guest_checkin_date")
    private LocalDate checkinDate;

    @Column(name="reservation_guest_checkout_date")
    private LocalDate checkoutDate;

    //Por ejemplo: pendiente, confirmado, cancelado, etc.
    @Column(name="reservation_guest_status")
    private String guestStatus;

    //Si cada huésped tiene un costo asociado en la reserva
    @Column(name="reservation_guest_cost")
    private BigDecimal guestCost;

    //notes
    @Column(name="reservation_guest_notes")
    private String notes;


    @Column(name="reservation_guest_created_at")
    private LocalDateTime createdAt;

    @Column(name="reservation_guest_updated_at")
    private LocalDateTime updatedAt;

//    @ManyToOne
//    @JoinColumn(name="reservation_guest_reservation_id")
//    private Reservation reservation;
//
//    @ManyToOne
//    @JoinColumn(name = "reservation_guest_guest_id")
//    private Guest guest;


}
