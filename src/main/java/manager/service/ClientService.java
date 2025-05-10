package manager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import manager.dto.ClientRequestDTO;
import manager.dto.ClientResponseDTO;
import manager.entity.Client;
import manager.mapper.ClientMapper;
import manager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        try {
            Client client = clientMapper.toEntity(dto);
            return clientMapper.toDTO(clientRepository.save(client));
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("System could not create client");
        }
    }

    public ClientResponseDTO getClientById(Long idNumber) {
        return clientRepository.findById(idNumber)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public List<ClientResponseDTO> getAllClients() {
        try {
            return clientRepository.findAll()
                    .stream()
                    .map(clientMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("System could not get clients");
        }
    }

    public boolean deleteClient(Long idNumber) {
        try {
            clientRepository.deleteById(idNumber);
            return true;
        }catch (EntityNotFoundException e){
            return false;
        }
    }

    public ClientResponseDTO updateClient(Long idNumber, ClientRequestDTO dto) {
        Client existing = clientRepository.findById(idNumber)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        Client updated = clientMapper.toEntity(dto);
        updated.setIdNumber(idNumber);
        try {
            return clientMapper.toDTO(clientRepository.save(updated));
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("System could not update client");
        }
    }
}

