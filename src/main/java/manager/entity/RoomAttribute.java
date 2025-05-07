package manager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_attribute")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAttribute{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomAttributeId;

    @Column(unique = true, nullable = false,name = "room_attribute_name")
    private String roomAttributeName;

    @Column( nullable = false,name = "room_attribute_description")
    private String roomAttributeDescription;

    @Column(unique = true, nullable = false,name = "room_attribute_photo")
    private String roomAttributePhotoUrl;

    @Column(name = "room_attribute_category_id")
    private Integer roomCategoryId;

    @ManyToOne
    @JoinColumn(name = "room_attribute_category_id")
    private RoomCategory roomCategory;

}
