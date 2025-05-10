package manager.repository;

import manager.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    //List<Fee> findByRoomCategoryId(Integer customerId);
    List<Fee> findByRoomCategoryRoomCategoryId(Integer roomCategoryId);

}
