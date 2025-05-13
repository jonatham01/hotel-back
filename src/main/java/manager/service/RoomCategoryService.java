package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.RoomCategoryRequest;
import manager.dto.RoomCategoryResponse;
import manager.entity.Hotel;
import manager.entity.RoomCategory;
import manager.entity.RoomCategoryDisponibility;
import manager.exception.RoomCategoryException;
import manager.mapper.RoomCategoryMapper;
import manager.repository.HotelRepository;
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
    private final HotelRepository hotelRepository;

    public RoomCategoryResponse createRoomCategory(RoomCategoryRequest roomCategoryRequest) {
        RoomCategory roomCategory = RoomCategoryMapper.dtoToEntity(roomCategoryRequest);
        Hotel hotel =hotelRepository.findById(roomCategoryRequest.getRoomCategoryHotelId()).orElseThrow(
                ()->new RuntimeException("System could find hotel")
        );
        roomCategory.setHotel(hotel);
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

    //PENDIENTE DE TEST CUANDO HAGA DISPONIBILITY
    public List<RoomCategoryResponse> findByDisponibility(LocalDate date) {
        return findAll().stream()
                .filter( roomCategoryResponse -> {
                    Optional<RoomCategoryDisponibility> roomCategoryDisponibility = disponibilityRepository.findByCategoryIdAndDate(roomCategoryResponse.getRoomCategoryId(),date);
                        if(roomCategoryDisponibility.isEmpty()) return true;
                        return roomCategoryDisponibility.get().getDisponibilityLevel() >0;
                        }
                ).toList();
    }
    public List<RoomCategoryResponse> findByParameters(String name, Double min, Double max, Integer hotelId){
        List<RoomCategory> roomCategory;
        boolean isNameNullOrEmpty = (name == null || name.trim().isEmpty());
        boolean isMinNull = (min == null);
        boolean isMaxNull = (max == null);

        if(isNameNullOrEmpty && isMinNull && isMaxNull){
            roomCategory = repository.findByHotel_HotelId(hotelId);
        }else{
            /// Si name es cadena vacía, se debe pasar como null para que funcione la condición SQL
            String nameFilter = isNameNullOrEmpty ? null : name;

            roomCategory = repository.findRoomCategories(min, max, hotelId, nameFilter);
        }
        if(roomCategory.isEmpty())RoomCategoryException.exception("System could not find any Room category");
        return roomCategory.stream()
                .map(RoomCategoryMapper::entityToDto)
                .toList();
    }
    public RoomCategoryResponse update(RoomCategoryRequest dto, Integer id) {
        RoomCategory roomCategory = repository.findById(id).orElseThrow(()->new RuntimeException("System could not find room category"));
        roomCategory.setRoomCategoryName(dto.getRoomCategoryName());
        roomCategory.setRoomCategoryPrice(dto.getRoomCategoryPrice());
        roomCategory.setRoomCategoryDescription(dto.getRoomCategoryDescription());
        if(roomCategory.getHotel().getHotelId() !=dto.getRoomCategoryHotelId()){
            roomCategory.setHotel(hotelRepository.findById(dto.getRoomCategoryHotelId()).orElseThrow(()->new RuntimeException("System could not find hotel")));
        }
       try {
           RoomCategory updatedRoomCategory = repository.save(roomCategory);
           return RoomCategoryMapper.entityToDto(updatedRoomCategory);
       }catch (Exception e) {
           throw new RuntimeException ("Room category could not be updated. Try again");
       }
    }

    public void deleteById(Integer id) {
        if (!repository.existsById(id)) RoomCategoryException.dontExist();
        else{
            repository.deleteById(id);
            if (repository.existsById(id)) RoomCategoryException.exception("Room category could not be deleted. Try again");
        }
    }
}
