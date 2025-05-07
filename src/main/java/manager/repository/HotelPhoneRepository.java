package manager.repository;

import manager.entity.HotelPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelPhoneRepository extends JpaRepository<HotelPhone, String> {
}
