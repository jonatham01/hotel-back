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
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping(path="/new")
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest hotelRequest) {
        Hotel createdHotel =hotelService.createHotel(newHotelToHotel(hotelRequest));
        return ResponseEntity.ok().body( hotelToHotelResponse(createdHotel) );
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@RequestBody HotelRequest hotel, @PathVariable int id) {
        Hotel updatedHotel = hotelService.updateHotel(hotel,id);
        return ResponseEntity.ok().body(hotelToHotelResponse(updatedHotel));
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Boolean> deleteHotel(@PathVariable Integer id) {
        return ResponseEntity.ok().body(hotelService.deleteHotel(id));

    }

    @GetMapping(path="/show/all")
    public ResponseEntity<List<HotelResponse>> showHotels(){
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok().body(hotels.stream().map(hotel -> hotelToHotelResponse(hotel)).toList());
    }

    @GetMapping(path="/find/{id}")
    public ResponseEntity<HotelResponse> findHotelById(@PathVariable Integer id){
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok().body(hotelToHotelResponse(hotel));
    }

    @GetMapping(path="/find/attributes")
    public ResponseEntity<List<HotelResponse>> findHotelById(@RequestParam String name, @RequestParam String country,@RequestParam String city,@RequestParam String state,@RequestParam String address){
        List<Hotel> hotels = hotelService.searchHotels(name, country, city, state, address);
        return ResponseEntity.ok(hotels.stream().map(hotel -> hotelToHotelResponse(hotel)).toList());
    }




}
