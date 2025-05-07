package manager.mapper;

import manager.dto.ClientRequestDTO;
import manager.dto.ClientResponseDTO;
import manager.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();
        client.setTypeIdentifier(dto.getTypeIdentifier());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setAge(dto.getAge());
        client.setGender(dto.getGender());
        client.setStatus(dto.getStatus());
        client.setOriginCountry(dto.getOriginCountry());
        client.setLiveCountry(dto.getLiveCountry());
        client.setKind(dto.getKind());
        client.setBirthDate(dto.getBirthDate());
        return client;
    }

    public ClientResponseDTO toDTO(Client client) {
        return ClientResponseDTO.builder()
                .idNumber(client.getIdNumber())
                .typeIdentifier(client.getTypeIdentifier())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .age(client.getAge())
                .gender(client.getGender())
                .status(client.getStatus())
                .originCountry(client.getOriginCountry())
                .liveCountry(client.getLiveCountry())
                .kind(client.getKind())
                .birthDate(client.getBirthDate())
                .reservationCount(client.getReservations() != null ? client.getReservations().size() : 0)
                .build();
    }
}

