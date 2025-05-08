package manager.service;
import lombok.RequiredArgsConstructor;
import manager.dto.HotelPhoneRequest;
import manager.entity.Hotel;
import manager.entity.HotelPhone;
import manager.exception.HotelPhoneException;
import manager.repository.HotelPhoneRepository;
import manager.repository.HotelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelPhoneService {
    private final HotelPhoneRepository hotelPhoneRepository;
    private final HotelRepository hotelRepository;

    public HotelPhone createHotelPhone(HotelPhoneRequest dto) {
        try {
            Hotel hotel = hotelRepository.findById(dto.getHotelId()).orElse(null);
            HotelPhone hotelPhone = new HotelPhone();
            hotelPhone.setHotel(hotel);
            HotelPhoneException.validate(hotelPhone);
            return hotelPhoneRepository.save(hotelPhone);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while saving hotel phone", e);
        }
    }


    public List<HotelPhone> getAll() {
        try {
            List<HotelPhone> hotelPhones = hotelPhoneRepository.findAll();
            if (hotelPhones.isEmpty()) throw new RuntimeException();
            return hotelPhones;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while retrieving hotel phones", e);
        }
    }

    public HotelPhone getHotelPhoneByNumber(String number) {
        try {
            return hotelPhoneRepository.findById(number).orElseThrow(RuntimeException::new);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while retrieving hotel phone.");
        }
    }

    public HotelPhone updateHotelPhone(HotelPhone hotelPhone) {
        HotelPhoneException.validate(hotelPhone);
        try {
            return hotelPhoneRepository.save(hotelPhone);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database constraint violated while updating hotel phone", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while updating hotel phone", e);
        }
    }

    public boolean deleteHotelPhone(String number) {
        if (!hotelPhoneRepository.existsById(number)) {
            throw new RuntimeException("Hotel phone does not exist");
        }
        try {
            hotelPhoneRepository.deleteById(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
