package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "room_category_disponibility")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCategoryDisponibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="category_id")
    private Integer categoryId;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="date")
    private LocalDate date;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="occupancy")
    private Integer occupancy;
    @Column(name="disponibility_level")
    private Integer disponibilityLevel;
    //@ManyToOne
    //@JoinColumn(name = "category_id")
    //private RoomCategory roomCategory;

    @OneToMany(mappedBy = "disponibility") //permite ver las habitaciones ocupadas
    private List<RoomCategoryDisponibilityRoom> dontAvailableRooms;

}
