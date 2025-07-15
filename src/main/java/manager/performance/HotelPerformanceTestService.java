package manager.performance;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import manager.entity.Hotel;
import manager.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelPerformanceTestService {
    private final HotelRepository hotelRepository;

    //@PostConstruct
    public void runPerformanceTest() {
        long start = System.currentTimeMillis();
        List<Hotel> hotels = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Hotel hotel = new Hotel();
            hotel.setHotelName("Hotel " + i);
            hotel.setHotelAddress("Address " + i);
            hotel.setHotelCity("City " + (i % 100));
            hotel.setHotelState("State " + (i % 10));
            hotel.setHotelCountry("Country " + (i % 5));
            hotels.add(hotel);
        }
        hotelRepository.saveAll(hotels); // Bulk insert
        long end = System.currentTimeMillis();
        System.out.println("Tiempo total de inserción: " + (end - start) + " ms");
    }
}

