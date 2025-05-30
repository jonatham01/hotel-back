package manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
public class ClientResponseDTO {
    private Long idNumber;
    private String typeIdentifier;
    private String firstName;
    private String lastName;
    private String email;
    private Short age;
    private String gender;
    private String status;
    private String originCountry;
    private String liveCountry;

    private String kind;
    private LocalDate birthDate;
    private int reservationCount;
}

