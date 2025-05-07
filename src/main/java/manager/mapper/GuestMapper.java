package manager.mapper;

import lombok.AllArgsConstructor;
import manager.dto.GuestRequestDTO;
import manager.dto.GuestResponseDTO;
import manager.entity.Guest;
import manager.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GuestMapper {
    private final HotelRepository hotelRepository;

    public static Guest toEntity(GuestRequestDTO dto) {
        Guest guest = new Guest();
        guest.setTypeIdentifier(dto.getTypeIdentifier());
        guest.setFirstName(dto.getFirstName());
        guest.setLastName(dto.getLastName());
        guest.setEmail(dto.getEmail());
        guest.setAge(dto.getAge());
        guest.setGender(dto.getGender());
        guest.setStatus(dto.getStatus());
        guest.setOriginCountry(dto.getOriginCountry());
        guest.setLiveCountry(dto.getLiveCountry());
        guest.setGuestKind(dto.getGuestKind());
        guest.setGuestBirthDate(dto.getGuestBirthDate());
        guest.setGuestStartDate(dto.getGuestStartDate());
        guest.setGuestEndDate(dto.getGuestEndDate());
        return guest;
    }

    public static GuestResponseDTO toDTO(Guest guest) {
        GuestResponseDTO dto = new GuestResponseDTO();
        dto.setIdNumber(guest.getIdNumber());
        dto.setTypeIdentifier(guest.getTypeIdentifier());
        dto.setFirstName(guest.getFirstName());
        dto.setLastName(guest.getLastName());
        dto.setEmail(guest.getEmail());
        dto.setAge(guest.getAge());
        dto.setGender(guest.getGender());
        dto.setStatus(guest.getStatus());
        dto.setOriginCountry(guest.getOriginCountry());
        dto.setLiveCountry(guest.getLiveCountry());

        dto.setGuestKind(guest.getGuestKind());
        dto.setGuestBirthDate(guest.getGuestBirthDate());
        dto.setGuestStartDate(guest.getGuestStartDate());
        dto.setGuestEndDate(guest.getGuestEndDate());

        dto.setHotelName(guest.getHotel() != null ? guest.getHotel().getHotelName() : null);
        return dto;
    }
}
