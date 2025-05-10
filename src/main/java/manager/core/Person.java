package manager.core;

import jakarta.persistence.Column;
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
    @Column(unique = true, nullable = false, name = "id_number")
    private BigInteger idNumber;
    @Column(name = "type_identifier")
    private String typeIdentifier;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private Short age;
    private String gender;
    private String status;
    @Column(name = "origin_country")
    private String originCountry;
    @Column(name = "live_country")
    private String liveCountry;

}
