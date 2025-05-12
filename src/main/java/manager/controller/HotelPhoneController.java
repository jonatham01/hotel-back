package manager.controller;

import manager.dto.HotelPhoneRequest;
import manager.dto.HotelPhoneResponse;
import manager.entity.HotelPhone;
import manager.mapper.HotelPhoneMapper;
import manager.service.HotelPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("hotelphones")
public class HotelPhoneController {
    @Autowired
    private HotelPhoneService hotelPhoneService;

    @GetMapping
    public ResponseEntity<List<HotelPhoneResponse>> getHotelPhones() {
        List<HotelPhoneResponse> hotelPhoneResponses = hotelPhoneService.getAll().stream().map(
                HotelPhoneMapper::toHotelPhoneResponse
        ).toList();
        return ResponseEntity.ok(hotelPhoneResponses);
    }
    @GetMapping("bynumber")
    public ResponseEntity<HotelPhoneResponse> getHotelPhone(@RequestParam String number) {
        HotelPhone hotelPhone= hotelPhoneService.getHotelPhoneByNumber(number);
        return ResponseEntity.ok(HotelPhoneMapper.toHotelPhoneResponse(hotelPhone));
    }
    @PostMapping
    public ResponseEntity<HotelPhoneResponse> createHotelPhone(@RequestBody HotelPhoneRequest hotelPhone) {
        HotelPhone number = hotelPhoneService.createHotelPhone(hotelPhone);
        return ResponseEntity.ok(HotelPhoneMapper.toHotelPhoneResponse(number));
    }
    @PutMapping(path = "/{oldNumber}")
    public ResponseEntity<HotelPhoneResponse> updateHotelPhone(@RequestBody HotelPhone hotelPhone, @PathVariable String oldNumber) {
        HotelPhone number = hotelPhoneService.updateHotelPhone(hotelPhone,oldNumber);
        return ResponseEntity.ok(HotelPhoneMapper.toHotelPhoneResponse(number));

    }
    @DeleteMapping(path = "/{number}")
    public ResponseEntity<Boolean> deleteHotelPhone(@PathVariable String number) {
        boolean response = hotelPhoneService.deleteHotelPhone(number);
        return ResponseEntity.ok().body(response);
    }


}
