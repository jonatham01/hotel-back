package manager.repository;

import manager.entity.HotelPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelPhoneRepository extends JpaRepository<HotelPhone, Short> {
    Optional<HotelPhone> findByNumber(String phone);
}
