package manager.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class GuestResponseDTO {
    private BigInteger idNumber;
    private String typeIdentifier;
    private String firstName;
    private String lastName;
    private String email;
    private Short age;
    private String gender;
    private String status;
    private String originCountry;
    private String liveCountry;

    private String guestKind;
    private LocalDate guestBirthDate;
    private LocalDate guestStartDate;
    private LocalDate guestEndDate;
    private String hotelName;
}
