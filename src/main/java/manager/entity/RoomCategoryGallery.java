package manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "room_gallery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomCategoryGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_category_gallery_id")
    private BigInteger id;

    @Column(name="room_category_gallery_tittle", unique = true, nullable = false, length = 50)
    private String tittle;
    @Column(name="room_category_description", nullable = false, length = 200)
    private String roomGalleryDescription;
    @Column(name="room_category_gallery_image", unique = true, nullable = false)
    private String roomGalleryImageUrl;

    @Column(name = "room_category_gallery_category_id", insertable = false, updatable = false)
    private Integer roomCategoryId;

    @ManyToOne
    @JoinColumn(name="room_category_gallery_category_id")
    private RoomCategory roomCategory;
}

