package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.PaymentResponseDTO;
import manager.dto.PaymentTransactionRequestDTO;
import manager.dto.PaymentTransactionResponseDTO;
import manager.entity.PaymentTransaction;
import manager.mapper.PaymentTransactionMapper;
import manager.repository.PaymentRepository;
import manager.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentTransactionMapper paymentTransactionMapper;

    public PaymentTransactionResponseDTO save(PaymentTransactionRequestDTO dto) {
        PaymentTransaction paymentTransaction = paymentTransactionMapper.mapToEntity(dto);
        try{
            paymentTransaction = paymentTransactionRepository.save(paymentTransaction);
        }catch (Exception e){
            throw new RuntimeException("Error saving PaymentTransaction");
        }
        PaymentTransactionResponseDTO responseDTO = PaymentTransactionMapper.mapToDTO(paymentTransaction);
        Long clientId =  paymentRepository.findById(responseDTO.getPaymentId()).orElseThrow().getPaymentClientId();
        responseDTO.setClientId(clientId);
        return responseDTO;
    }

    public PaymentTransactionResponseDTO findById(int id) {
        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Payment not found"));
        PaymentTransactionResponseDTO responseDTO = PaymentTransactionMapper.mapToDTO(paymentTransaction);
        return responseDTO;
    }
    public List<PaymentTransactionResponseDTO> findAll() {
        List<PaymentTransaction> paymentTransactions = paymentTransactionRepository.findAll();
        List<PaymentTransactionResponseDTO> responseDTOs = new ArrayList<>();
        for (PaymentTransaction paymentTransaction : paymentTransactions) {
            PaymentTransactionResponseDTO responseDTO = PaymentTransactionMapper.mapToDTO(paymentTransaction);
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }
    public PaymentTransactionResponseDTO update(int id, PaymentTransactionRequestDTO dto) {
        PaymentTransaction paymentTransaction = paymentTransactionMapper.mapToEntity(dto);
        paymentTransaction.setPaymentCashId(id);
        paymentTransaction = paymentTransactionRepository.save(paymentTransaction);
        PaymentTransactionResponseDTO responseDTO = PaymentTransactionMapper.mapToDTO(paymentTransaction);
        Long clientId =  paymentRepository.findById(responseDTO.getPaymentId()).orElseThrow().getPaymentClientId();
        responseDTO.setClientId(clientId);
        return responseDTO;
    }
    public void delete(int id) {
        try {
            paymentTransactionRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error deleting PaymentTransaction");
        }
    }

}
