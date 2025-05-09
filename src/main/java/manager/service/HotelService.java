package manager.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import manager.dto.HotelRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import manager.entity.Hotel;
import manager.exception.HotelException;
import manager.repository.HotelRepository;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        try {
            List<Hotel> hotels = hotelRepository.findAll();
            return  hotels;
        } catch (DataAccessException e) {
            throw new RuntimeException("System could not show hotels",e);
        }

    }
    public Hotel getHotelById(int id) {
        try {
            return hotelRepository.findById(id).get();
        }catch (DataAccessException e) {
            throw new RuntimeException("System could not find hotel " );
        }
    }
    
    public Hotel createHotel(Hotel hotel) {
        try {
            HotelException.validate(hotel);
            Hotel createdHotel=  hotelRepository.save(hotel);
            return createdHotel;
        }catch (DataAccessException e) {
            throw new RuntimeException("System could not save new hotel, try again.");
        }
    }
    public Hotel updateHotel(HotelRequest dto, Integer id) {
        //HotelException.validate(hotel);
        Optional<Hotel> optional =hotelRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RuntimeException("System could not find hotel");
        }
        try{
            Hotel hotel = optional.get();
            hotel.setHotelName(dto.getName());
            hotel.setHotelAddress(dto.getAddress());
            hotel.setHotelCity(dto.getCity());
            hotel.setHotelState(dto.getState());
            hotel.setHotelCountry(dto.getCountry());
            Hotel updatedHotel = hotelRepository.save(hotel);
            return updatedHotel;
        }catch (DataAccessException e) {
            throw new RuntimeException("System could not update hotel, try again.");
        }
    }

    public boolean deleteHotel(int id) {
       try{
           hotelRepository.deleteById(id);
           return true;
       }
       catch (DataAccessException e) {
           return false;
       }
    }

    public List<Hotel> searchHotels(String name, String country, String city, String state, String address) {
        List<Hotel> results = hotelRepository.findAll();

        if (name != null && StringUtils.hasText(name)) {
            results = results.stream().filter( data -> name.equals(data.getHotelName())).toList();
        }
        if (country != null && !country.isEmpty()) {
            results = results.stream().filter( data -> country.equals(data.getHotelCountry())).toList();
        }
        if (city != null && !city.isEmpty()) {
            results = results.stream().filter( data -> city.equals(data.getHotelCity())).toList();
        }
        if (state != null && !state.isEmpty()) {
            results = results.stream().filter( data -> state.equals(data.getHotelState())).toList();
        }
        if (address != null && !address.isEmpty()) {
            results = results.stream().filter( data -> address.equals(data.getHotelAddress())).toList();
            //results.retainAll(hotelRepository.findByHotelAddress(address));
        }
        return results;
    }

}
