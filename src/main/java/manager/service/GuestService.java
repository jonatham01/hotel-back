package manager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import manager.dto.GuestRequestDTO;
import manager.dto.GuestResponseDTO;
import manager.entity.Guest;
import static manager.mapper.GuestMapper.*;

import manager.mapper.GuestMapper;
import manager.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;


    public GuestResponseDTO createGuest(GuestRequestDTO dto) {
        try{
            Guest guest = toEntity(dto);
            return toDTO(guestRepository.save(guest));
        }catch(Exception e){
            throw new EntityNotFoundException("System could not create guest");
        }
    }

    public GuestResponseDTO getGuestById(BigInteger idNumber) {
        return guestRepository.findById(idNumber)
                .map(GuestMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));
    }

    public List<GuestResponseDTO> getAllGuests() {
       try {
           return guestRepository.findAll()
                   .stream()
                   .map(GuestMapper::toDTO)
                   .collect(Collectors.toList());
       }
        catch(Exception e){
            throw new EntityNotFoundException("System could find all guests");
        }
    }

    public boolean deleteGuest(BigInteger idNumber) {
       try {
           guestRepository.deleteById(idNumber);
           return true;
       }catch(Exception e){
           throw new EntityNotFoundException("System could not delete guest");
       }
    }

    public GuestResponseDTO updateGuest(BigInteger idNumber, GuestRequestDTO dto) {
        Guest guest = guestRepository.findById(idNumber)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));

        Guest updated = toEntity(dto);
        updated.setIdNumber(idNumber);
        try {
            return toDTO(guestRepository.save(updated));
        }catch(Exception e){
            throw new EntityNotFoundException("System could not update guest");
        }
    }
}

