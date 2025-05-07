package manager.controller;

import lombok.RequiredArgsConstructor;
import manager.dto.HotelPhoneResponse;
import manager.dto.HotelRequest;
import manager.dto.HotelResponse;
import manager.entity.Hotel;
import manager.security.ErrorResponse;
import manager.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static manager.mapper.HotelMapper.*;

@Controller
@RequestMapping(name = "hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping("new")
    public ResponseEntity<?> createHotel(@RequestBody HotelRequest hotelRequest) {
        Hotel createdHotel =hotelService.createHotel(newHotelToHotel(hotelRequest));
        return ResponseEntity.ok().body(createdHotel);
    }

    @PutMapping("update/one")
    public ResponseEntity<?> updateHotel(Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(hotel);
        return ResponseEntity.ok().body(hotelToHotelResponse(updatedHotel));
    }

    @DeleteMapping("delete/one")
    public ResponseEntity<?> deleteHotel(Integer id) {
        return ResponseEntity.ok().body(hotelService.deleteHotel(id));
    }

    @GetMapping("show/all")
    public ResponseEntity<?> showHotels(){
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok().body(hotels.stream().map(hotel -> hotelToHotelResponse(hotel)).toList());
    }

    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findHotelById(@PathVariable Integer id){
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok().body(hotelToHotelResponse(hotel));
    }

    @GetMapping("find/attributes")
    public ResponseEntity<?> findHotelById(@RequestParam String name, @RequestParam String country,@RequestParam String city,@RequestParam String state,@RequestParam String address){
        List<Hotel> hotels = hotelService.searchHotels(name, country, city, state, address);

        if (hotels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("System could not find hotels with that specified attributes");
        }
        return ResponseEntity.ok(hotels.stream().map(hotel -> hotelToHotelResponse(hotel)).toList());
    }




}
