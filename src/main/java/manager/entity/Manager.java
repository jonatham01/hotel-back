package manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import manager.core.Person;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "managers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Person {
    private String roll;
    private String department;
    private LocalDate birthDate;
    private LocalTime ingressDate;
    private String photoUrl;
    private String curriculumUrl;

}
