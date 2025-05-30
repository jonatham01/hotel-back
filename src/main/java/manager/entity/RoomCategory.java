package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_categories")
@Data
@AllArgsConstructor
public class RoomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_category_id")
    private Integer roomCategoryId;

    @Column(name = "room_category_name")
    private String roomCategoryName;

    @Column(name = "room_category_night_price")
    private Double roomCategoryPrice;

    @Column(name = "room_category_description")
    private String roomCategoryDescription;

    //@Column(name = "room_category_hotel_id",nullable = false)
    //private Integer roomCategoryHotelId;

    @ManyToOne()
    @JoinColumn(name = "room_category_hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "roomCategory")
    private List<Room> rooms;

    @OneToMany(mappedBy = "roomCategory")
    private List<RoomAttribute> roomAttributes;

    @OneToMany(mappedBy = "roomCategory")
    private List<RoomCategoryGallery> roomCategoryGalleries;

    public RoomCategory() {
        this.rooms = new ArrayList<>();
        this.roomAttributes = new ArrayList<>();
        this.roomCategoryGalleries = new ArrayList<>();
    }


}
