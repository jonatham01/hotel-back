package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomAttributeDto;
import manager.dto.RoomAttributeResponseDTO;
import manager.entity.RoomAttribute;
import manager.entity.RoomCategory;
import manager.exception.RoomAttributeException;
import manager.mapper.RoomAttributeMapper;
import manager.mapper.RoomCategoryMapper;
import manager.repository.RoomAttributeRepository;
import manager.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomAttributeService {
    @Value("${room-attribute.upload.path:src/main/resources/static/uploads}")
    private String uploadDir;

    private final RoomAttributeRepository roomAttributeRepository;
    private final RoomCategoryRepository roomCategoryRepository;

    public List<RoomAttributeResponseDTO> getAll() {
        try {
            return roomAttributeRepository.findAll()
                    .stream()
                    .map(RoomAttributeMapper::toDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("System could not find room attribute");
        }
    }

    public RoomAttributeResponseDTO getById(int id) {
        try {
            RoomAttribute attribute = roomAttributeRepository.findById(id).orElseThrow( () -> new RuntimeException());
            return RoomAttributeMapper.toDTO(attribute);
        }catch (Exception e) {
            throw new  RuntimeException("System could not find room attribute");
        }

    }
    public RoomAttributeResponseDTO getByName(String name) {
        RoomAttribute attribute= roomAttributeRepository.findByRoomAttributeName(name).orElseThrow(() -> new RuntimeException("System could not find room attribute"));
        return RoomAttributeMapper.toDTO(attribute);
    }


    public RoomAttributeResponseDTO create(RoomAttributeDto dto, MultipartFile file) throws IOException {
        if (roomAttributeRepository.existsByRoomAttributeName(dto.getRoomAttributeName())) {
            throw new IllegalArgumentException("Attribute name already exists");
        }
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        RoomCategory category = roomCategoryRepository.findById(dto.getRoomCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Room category not found"));

        RoomAttribute entity = RoomAttributeMapper.dtoToEntity(dto, "/uploads/" + filename);
        entity.setRoomCategory(category);
        RoomAttribute saved =roomAttributeRepository.save(entity);
        return RoomAttributeMapper.toDTO(saved);
    }

    public RoomAttributeResponseDTO update(int id, RoomAttributeDto dto, MultipartFile file)  {
        RoomAttribute existing = roomAttributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomAttribute not found"));

        // Eliminar imagen anterior si existe
        if (existing.getRoomAttributePhotoUrl() != null) {
            String oldImagePath = uploadDir + existing.getRoomAttributePhotoUrl().replace("/uploads", "");
            try {
                Files.deleteIfExists(Paths.get(oldImagePath));
            }catch (Exception e) {
                throw new RuntimeException("System could not find old image path");
            }
        }
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);
        try{
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e) {
            throw new RuntimeException("System could not save new image");
        }
        // Actualizar datos
        existing.setRoomAttributeName(dto.getRoomAttributeName());
        existing.setRoomAttributeDescription(dto.getRoomAttributeDescription());
        existing.setRoomAttributePhotoUrl("/uploads/" + filename);

        // Actualizar relación con RoomCategory
        RoomCategory category = roomCategoryRepository.findById(dto.getRoomCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Room category not found"));
        existing.setRoomCategory(category);

        RoomAttribute updated = roomAttributeRepository.save(existing);
        return RoomAttributeMapper.toDTO(updated);
    }


    public boolean delete(int id) {
        if(!roomAttributeRepository.existsById(id)) RoomAttributeException.dontExist();
        RoomAttribute existing = roomAttributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomAttribute not found"));

        // Eliminar imagen anterior si existe
        if (existing.getRoomAttributePhotoUrl() != null) {
            String oldImagePath = uploadDir + existing.getRoomAttributePhotoUrl().replace("/uploads", "");
            try {
                Files.deleteIfExists(Paths.get(oldImagePath));
            }catch (Exception e) {
                throw new RuntimeException("System could not delete old image path");
            }
        }
        //delete data
        try {
            roomAttributeRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            throw new RuntimeException("System could not delete room attribute");
        }
    }

}
