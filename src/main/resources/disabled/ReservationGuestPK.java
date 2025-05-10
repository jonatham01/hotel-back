package disabled;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;


@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationGuestPK  {

    @Column(name="reservation_guest_reservation_id")
    private BigInteger reservationId;

    @Column(name="reservation_guest_guest_id")
    private BigInteger guest_id;

}
