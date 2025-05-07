package manager.repository;

import manager.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByHotelName(String hotelName);
    List<Hotel> findByHotelCity(String hotelCity);
    List<Hotel> findByHotelState(String hotelState);
    List<Hotel> findByHotelCountry(String hotelCountry);
    List<Hotel> findByHotelAddress(String hotelAddress);

}
