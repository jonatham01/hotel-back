package manager.service;

import manager.entity.RoomCategory;
import manager.entity.RoomCategoryGallery;
import manager.repository.RoomCategoryGalleryRepository;
import manager.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class RoomCategoryGalleryService {

    @Value("${gallery.upload.path:src/main/resources/static/uploads}")
    private String uploadDir;

    private final RoomCategoryGalleryRepository repository;
    private final RoomCategoryRepository categoryRepository;

    @Autowired
    public RoomCategoryGalleryService(RoomCategoryGalleryRepository repository, RoomCategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public RoomCategoryGallery save(String tittle, String description, MultipartFile file, Integer categoryId) throws IOException {
        if (repository.existsByTittle(tittle)) {
            throw new IllegalArgumentException("Title already exists");
        }

        // Ruta y nombre de archivo
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        RoomCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room category ID"));

        RoomCategoryGallery gallery = new RoomCategoryGallery();
        gallery.setTittle(tittle);
        gallery.setRoomGalleryDescription(description);
        gallery.setRoomGalleryImageUrl("/uploads/" + filename);
        gallery.setRoomCategory(category);
        gallery.setRoomCategoryId(categoryId);

        return repository.save(gallery);
    }

    public List<RoomCategoryGallery> getAll() {
        return repository.findAll();
    }

    public void delete(BigInteger id) {
        repository.deleteById(id);
    }
}
