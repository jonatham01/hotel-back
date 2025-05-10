package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.FeeRequestDTO;
import manager.dto.FeeResponseDTO;
import manager.entity.Fee;
import manager.entity.RoomCategory;
import manager.mapper.FeeMapper;
import manager.repository.FeeRepository;
import manager.repository.RoomCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService {
    private final FeeRepository feeRepository;
    private final FeeMapper feeMapper;
    private final RoomCategoryRepository roomCategoryRepository;

    public FeeResponseDTO save(FeeRequestDTO feeRequestDTO) {
        try{
            Fee fee = feeMapper.toEntity(feeRequestDTO);
            Fee savedFee= feeRepository.save(fee);
            return feeMapper.toDto(savedFee);

        }catch (Exception e){
            throw new RuntimeException("Save fee failed, try again");
        }
    }

    public List<FeeResponseDTO> findAll() {
        try{
            List<Fee> fees = feeRepository.findAll();
            return fees.stream().map(feeMapper::toDto)
                    .toList();
        }catch (Exception e){
            throw new RuntimeException("Find fees failed, try again");
        }
    }

    public FeeResponseDTO findById(Long id) {
        try{
            return  feeMapper.toDto(
                    feeRepository.findById(id).get()
            );
        }catch (Exception e){ throw new RuntimeException("Find fee failed, try again"); }
    }

    public List<FeeResponseDTO> findByRoomCategoryId(Integer id) {
        try{
            List<Fee> fees = feeRepository.findByRoomCategoryRoomCategoryId(id);
            return fees.stream().map(feeMapper::toDto)
                    .toList();
        }catch (Exception e){
            throw new RuntimeException("Find fees failed, try again");
        }
    }

    public FeeResponseDTO update(FeeRequestDTO dto, Long id) {
      try {
          RoomCategory roomCategory= roomCategoryRepository.findById(dto.getRoomCategoryId()).orElseThrow(() -> new RuntimeException("Room category not found"));
          Fee fee = feeRepository.findById(id).get();
          fee.setPublicFee(dto.getPublicFee());
          fee.setDate(dto.getDate());
          fee.setIncrementRate(dto.getIncrementRate());
          fee.setRoomCategory(roomCategory);
          feeRepository.save(fee);
          return feeMapper.toDto(fee);
      }catch (Exception e){
          throw new RuntimeException("Update fee failed, try again"); }
        }

    public void delete(Long id) {
        try{
            feeRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Delete fee failed, try again");
        }
    }

}
