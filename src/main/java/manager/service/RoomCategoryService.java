package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomCategoryRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.RoomCategory;
import manager.entity.RoomCategoryDisponibility;
import manager.exception.RoomCategoryException;
import manager.mapper.RoomCategoryMapper;
import manager.repository.RoomCategoryDisponibilityRepository;
import manager.repository.RoomCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomCategoryService {
    private final RoomCategoryRepository repository ;
    private final RoomCategoryDisponibilityRepository disponibilityRepository ;

    public RoomCategoryResponse createRoomCategory(RoomCategoryRequest roomCategoryRequest) {
        RoomCategory roomCategory = RoomCategoryMapper.dtoToEntity(roomCategoryRequest);
        RoomCategoryException.validate(roomCategory);
        try {
            RoomCategory savedRoomCategory = repository.save(roomCategory);
            return RoomCategoryMapper.entityToDto(savedRoomCategory);
        }
        catch (Exception e) {
            RoomCategoryException.exception("Room category could not be saved. Try again");
        }
        return null;
    }
    public List<RoomCategoryResponse> findAll() {
        try {
            return repository.findAll().stream()
                    .map(RoomCategoryMapper::entityToDto)
                    .toList();
        }
        catch (Exception e) {
            throw new RuntimeException("Room categories could not be found. Try again");
        }
    }

    public RoomCategoryResponse findById(Integer id) {
        Optional<RoomCategory> roomCategory = repository.findById(id);
        if (roomCategory.isPresent()) return RoomCategoryMapper.entityToDto(roomCategory.get());
        RoomCategoryException.exception("Room category could not be found. Try again");
        return null;
    }
    public List<RoomCategoryResponse> findByDisponibility(LocalDate date) {
        return findAll().stream()
                .filter( roomCategoryResponse -> {
                    Optional<RoomCategoryDisponibility> roomCategoryDisponibility = disponibilityRepository.findByRoomCategoryIdAAndDate(roomCategoryResponse.getRoomCategoryId(),date);
                        if(roomCategoryDisponibility.isEmpty()) return true;
                        return roomCategoryDisponibility.get().getDisponibilityLevel() >0;
                        }
                ).toList();
    }
    public List<RoomCategoryResponse> findByParameters(String name, double min, double max, Integer hotelId){
        if(name==null && Double.isNaN(min) && Double.isNaN(max)){
            List<RoomCategory> roomCategory = repository.findByRoomCategoryHotelId(hotelId);
            if(roomCategory.isEmpty())RoomCategoryException.exception("System could not find any Room category");
            return roomCategory.stream().map(RoomCategoryMapper::entityToDto).toList();
        }else{
            List<RoomCategory> roomCategory = repository.findRoomCategories(min,max,hotelId,name);
            if(roomCategory.isEmpty())RoomCategoryException.exception("System could not find any Room category");
            return roomCategory.stream().map(RoomCategoryMapper::entityToDto).toList();
        }
    }
    public RoomCategoryResponse update(RoomCategory roomCategory) {
       if (!repository.existsById(roomCategory.getRoomCategoryId())) RoomCategoryException.dontExist();
       RoomCategoryException.validate(roomCategory);
       RoomCategory updatedRoomCategory = repository.save(roomCategory);
       if(updatedRoomCategory.getRoomCategoryId() ==null) RoomCategoryException.exception("Room category could not be updated. Try again");
       return RoomCategoryMapper.entityToDto(updatedRoomCategory);
    }

    public void deleteById(Integer id) {
        if (!repository.existsById(id)) RoomCategoryException.dontExist();
        else{
            repository.deleteById(id);
            if (repository.existsById(id)) RoomCategoryException.exception("Room category could not be deleted. Try again");
        }
    }
}
