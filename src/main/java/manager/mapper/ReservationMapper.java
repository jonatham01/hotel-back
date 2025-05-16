package manager.mapper;
import manager.dto.ReservationRequestDTO;
import manager.dto.ReservationResponseDTO;
import manager.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationStartDate(dto.getReservationStartDate());
        reservation.setReservationEndDate(dto.getReservationEndDate());
        reservation.setReservationTotalValue(dto.getReservationTotalValue());
        reservation.setReservationCheckinStatus("Reservado");
        reservation.setReservationCheckoutStatus("Pendiente");
        reservation.setReservationPaymentStatus("Pendiente");
        //reservation.setReservationHotelId(dto.getReservationHotelId());
        //reservation.setReservationClientId(dto.getReservationClientId());

        // Relaciones @ManyToOne se asignan por fuera en el Service si es necesario (usando findById)
        return reservation;
    }

    public ReservationResponseDTO toDTO(Reservation reservation) {
        return ReservationResponseDTO.builder()
                .reservationId(reservation.getReservationId())
                .reservationStartDate(reservation.getReservationStartDate())
                .reservationEndDate(reservation.getReservationEndDate())
                .reservationTotalValue(reservation.getReservationTotalValue())
                .reservationCheckinStatus(reservation.getReservationCheckinStatus())
                .reservationCheckoutStatus(reservation.getReservationCheckoutStatus())
                .reservationHotelId(reservation.getReservationHotel().getHotelId())
                .reservationRoomId(reservation.getReservationRoom().getRoomId())
                .reservationRoomName((reservation.getReservationRoom().getName()))
                .reservationPaymentId(reservation.getReservationPayment().getId())
                .reservationClientId(reservation.getReservationClient().getIdNumber())
                .reservationClientName(reservation.getReservationClient().getFirstName() + " " + reservation.getReservationClient().getLastName())
                .build();
    }
}

