package manager.core;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
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

}
