package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_phone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String number;

    @ManyToOne()
    @JoinColumn(name="phone_hotel_id")
    private Hotel hotel;

}
