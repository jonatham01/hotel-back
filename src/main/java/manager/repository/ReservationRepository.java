package manager.repository;

import manager.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);
//    Optional<Reservation> findByReservationRoomId(BigInteger id);
//    Optional<Reservation> findByPaymentStatus(String status);

}

