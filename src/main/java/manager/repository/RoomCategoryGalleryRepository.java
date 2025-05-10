package manager.repository;

import manager.entity.RoomCategoryGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoomCategoryGalleryRepository extends JpaRepository<RoomCategoryGallery, Long> {
    boolean existsByTittle(String tittle);
    boolean existsByRoomGalleryImageUrl(String url);
}
