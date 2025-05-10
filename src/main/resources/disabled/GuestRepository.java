package disabled;

import manager.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface GuestRepository extends JpaRepository<Guest, BigInteger> {
}
