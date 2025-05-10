package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.RCDisponibilityRequest;
import manager.entity.RoomCategoryDisponibility;
import manager.exception.RCDisponibilityException;
import manager.exception.RoomCategoryException;
import manager.mapper.RoomCategoryDisponibilityMapper;
import manager.repository.RoomCategoryDisponibilityRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static manager.mapper.RoomCategoryDisponibilityMapper.mapDtoToRoomCategoryDisponibility;

@Service
@RequiredArgsConstructor
public class RoomCategoryDisponibilityService {
    private final RoomCategoryDisponibilityRepository repository;

    public RoomCategoryDisponibility createDisponibility(RCDisponibilityRequest dto) throws RCDisponibilityException {
        RoomCategoryDisponibility entity =  mapDtoToRoomCategoryDisponibility(dto);
        try {
            RoomCategoryDisponibility result = repository.save(entity);
            return result;
        }catch (Exception e) {
            throw new RCDisponibilityException("System could not create new disponibility");
        }
    }

    public List<RoomCategoryDisponibility> findAllDisponibility() throws RCDisponibilityException {
        try {
            return repository.findAll();
        }catch (Exception e) {
            throw new RCDisponibilityException("System could not find all disponibilities");
        }
    }
    public Optional<RoomCategoryDisponibility> findDisponibilityById(Long id) throws RCDisponibilityException {
        try {
            return repository.findById(id);
        }catch (Exception e) {
            throw new RCDisponibilityException("System could not find disponibility");
        }
    }
    public Optional<RoomCategoryDisponibility> findDisponibilityByRoomCategory(int id, LocalDate date) throws RCDisponibilityException {
        try{
            return repository.findByRoomCategoryIdAAndDate(id,date);
        }catch (Exception e) {
            throw new RCDisponibilityException("System could not find disponibility");
        }
    }
    public RoomCategoryDisponibility update(RCDisponibilityRequest dto, Long id) throws RCDisponibilityException {
        if (!repository.existsById(id)) throw new RCDisponibilityException("System could not find disponibility");
        try {
            RoomCategoryDisponibility entity = repository.findById(id).get();
            RoomCategoryDisponibility newEntity =  mapDtoToRoomCategoryDisponibility(dto);
            newEntity.setId(id);
            return repository.save(newEntity);
        }catch (Exception e) {
            throw new RCDisponibilityException("System could not update disponibility");
        }
    }



}
