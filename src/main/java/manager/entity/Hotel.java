package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name="hotel_name")
    private String hotelName;
    @Column(name="hotel_address")
    private String hotelAddress;
    @Column(name="hotel_city")
    private String hotelCity;
    @Column(name="hotel_state")
    private String hotelState;
    @Column(name="hotel_country")
    private String hotelCountry;

    @OneToMany(mappedBy = "hotel")
    private List<HotelPhone> hotelPhones;

    @OneToMany(mappedBy = "hotel")
    private List<RoomCategory> roomCategories;
    public Hotel() {
        this.hotelPhones = new ArrayList<>();
        this.roomCategories = new ArrayList<>();
    }
}
