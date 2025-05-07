package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.FeeRequestDTO;
import manager.dto.FeeResponseDTO;
import manager.entity.Fee;
import manager.mapper.FeeMapper;
import manager.repository.FeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService {
    private final FeeRepository feeRepository;
    private final FeeMapper feeMapper;

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
            return fees.stream().map(FeeMapper::toDto)
                    .toList();
        }catch (Exception e){
            throw new RuntimeException("Find fees failed, try again");
        }
    }

    public FeeResponseDTO findById(BigInteger id) {
        try{
            return  feeMapper.toDto(
                    feeRepository.findById(id).get()
            );
        }catch (Exception e){ throw new RuntimeException("Find fee failed, try again"); }
    }

    public List<FeeResponseDTO> findByRoomCategoryId(BigInteger id) {
        try{
            List<Fee> fees = feeRepository.findByRoomCategoryId(id);
            return fees.stream().map(FeeMapper::toDto)
                    .toList();
        }catch (Exception e){
            throw new RuntimeException("Find fees failed, try again");
        }
    }

    public FeeResponseDTO update(FeeRequestDTO dto, BigInteger id) {
      try {
          Fee fee = feeRepository.findById(id).get();
          fee.setPublicFee(dto.getPublicFee());
          fee.setDate(dto.getDate());
          fee.setIncrementRate(dto.getIncrementRate());
          fee.setRoomCategoryId(dto.getRoomCategoryId());
          feeRepository.save(fee);
          return feeMapper.toDto(fee);
      }catch (Exception e){
          throw new RuntimeException("Update fee failed, try again"); }
        }

    public void delete(BigInteger id) {
        try{
            feeRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Delete fee failed, try again");
        }
    }

}
