package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelPhone {
    @Id
    private String number;

    @Column(name = "phone_hotel_id",nullable = false)
    private Integer HotelId;

    @ManyToOne()
    @JoinColumn(name="phone_hotel_id")
    private Hotel hotel;

}
