package manager.service;

import lombok.RequiredArgsConstructor;
import manager.dto.ReservationRequestDTO;
import manager.dto.ReservationResponseDTO;
import manager.entity.*;
import manager.mapper.ReservationMapper;
import manager.repository.*;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final RoomCategoryDisponibilityRepository reservationCategoryDisponibilityRepository;
    private final RoomRepository roomRepository;
    private final RoomCategoryDisponibilityRoomRepository roomCategoryDisponibilityRoomRepository;
    private final RoomCategoryRepository roomCategoryRepository;
    private final PaymentRepository paymentRepository;
    private final HotelRepository hotelRepository;
    private final ClientRepository clientRepository;

    // método para convertir UUID a byte[16]
    public static byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
    public static List<LocalDate> getDatesBetween(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;

        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusDays(1);
        }

        return dates;
    }


    public List<ReservationResponseDTO> createReservation(ReservationRequestDTO dto) {
        List<Reservation> reservations = new ArrayList<>();
        List<LocalDate> dates = getDatesBetween(
          dto.getReservationStartDate(),dto.getReservationEndDate()
        );
        List<Integer> disabledRoomIdList  = new ArrayList<>();
        List<RoomCategoryDisponibilityRoom> dontAvailableRooms = new ArrayList<>();
        //añadimos las dontAvailableRooms a la lista
        //genero bucle para agregar todas las Room category disponibilities en una lista

        try {
            dates.stream().forEach( localDate -> {
                Optional<RoomCategoryDisponibility> disponibility = reservationCategoryDisponibilityRepository.findByCategoryIdAndDate(
                        dto.getReservationRoomCategoryId(),
                        localDate
                );
                if(disponibility.isPresent() ) {
                    dontAvailableRooms.addAll(disponibility.get().getDontAvailableRooms());
                }
            });
        }catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }


        try {
            //añadir todos los ids de los rooms, sino estan en la lista
            if(!dontAvailableRooms.isEmpty()) {
                dontAvailableRooms.forEach( roomCategoryDisponibilityRoom -> {
                    Integer roomId = roomCategoryDisponibilityRoom.getId().getRoomId();
                    if (!disabledRoomIdList.contains(roomId))  disabledRoomIdList.add(roomId);
                });
            }
            //find rooms and remove reserved ones. At the end, list only contain available rooms
            List<Room> allRooms = roomRepository.findByRoomStatusAndRoomCategoryRoomCategoryId("Disponible",dto.getReservationRoomCategoryId());

           if(!disabledRoomIdList.isEmpty()) {
               allRooms.removeAll(disabledRoomIdList);
           }
           //disponibilities creation to disable reserved rooms
            String name = roomCategoryRepository.findById(dto.getReservationRoomCategoryId()).get().getRoomCategoryName();
            dates.forEach(localDate -> {
                Optional<RoomCategoryDisponibility> reservationCategoryDisponibility = reservationCategoryDisponibilityRepository.findByCategoryIdAndDate(
                        dto.getReservationRoomCategoryId(),
                        localDate
                );
                RoomCategoryDisponibility roomCategoryDisponibility;

                if(!reservationCategoryDisponibility.isPresent()) {
                    roomCategoryDisponibility = new RoomCategoryDisponibility();
                    roomCategoryDisponibility.setCategoryId(dto.getReservationRoomCategoryId());
                    roomCategoryDisponibility.setCategoryName(name);
                    roomCategoryDisponibility.setDate(localDate);
                    roomCategoryDisponibility.setOccupancy(1);
                    roomCategoryDisponibility.setDisponibilityLevel(allRooms.size()-1);
                }else{
                    roomCategoryDisponibility = reservationCategoryDisponibility.get();
                    roomCategoryDisponibility.setOccupancy(reservationCategoryDisponibility.get().getOccupancy()+1);
                    roomCategoryDisponibility.setDisponibilityLevel(
                            allRooms.size()-reservationCategoryDisponibility.get().getOccupancy()
                            //reservationCategoryDisponibility.get().getDisponibilityLevel()-1
                    );
                }
                roomCategoryDisponibility.setQuantity(allRooms.size());
                RoomCategoryDisponibility savedDisponibility = reservationCategoryDisponibilityRepository.save(roomCategoryDisponibility);

                //create RoomCategoryDisponibilityRoom
                RoomCategoryDisponibilityRoomPK pk = new RoomCategoryDisponibilityRoomPK(
                        allRooms.getFirst().getRoomId(),
                        savedDisponibility.getId()
                );

                Room room= allRooms.getFirst();
                RoomCategoryDisponibilityRoom roomCategoryDisponibilityRoom =new RoomCategoryDisponibilityRoom();
                roomCategoryDisponibilityRoom.setId(pk);
                roomCategoryDisponibilityRoom.setRoom(room);
                roomCategoryDisponibilityRoom.setDisponibility(savedDisponibility);

                RoomCategoryDisponibilityRoom roomCategoryDisponibilityRoomResponse = roomCategoryDisponibilityRoomRepository.save(roomCategoryDisponibilityRoom);
                //allRooms.remove(allRooms.getFirst());
            });
            //FIN DEL BUCLE

            //CREATE Reservation
            Hotel hotel = hotelRepository.findById(dto.getReservationHotelId()).orElse(null);
            Client client = clientRepository.findById(dto.getReservationClientId()).orElse(null);
            Optional<Payment> optionalPayment = paymentRepository.findById(dto.getPaymentId());

            Reservation reservation = new Reservation();
            reservation.setReservationRoom(allRooms.getFirst());
            reservation.setReservationHotel(hotel);
            reservation.setReservationClient(client);
            reservation.setReservationPayment(optionalPayment.get());
            reservation.setReservationStartDate(dto.getReservationStartDate());
            reservation.setReservationEndDate(dto.getReservationEndDate());
            reservation.setReservationTotalValue(dto.getReservationTotalValue());
            reservation.setReservationCheckinStatus("Pendiente");
            reservation.setReservationCheckoutStatus("Pendiente");
            reservation.setReservationPaymentStatus("Realizado");

            Reservation savedReservation = reservationRepository.save(reservation);
            reservations.add(savedReservation);

            return reservations.stream()
                    .map(reservationMapper::toDTO)
                    .toList();

        }catch (Exception e){
            throw new EntityNotFoundException("Error while creating reservation");
        }

    }

    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReservationResponseDTO getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }

    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO dto) {
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        Reservation updated = reservationMapper.toEntity(dto);
        updated.setReservationId(id); // Aseguramos que se actualiza el existente

        return reservationMapper.toDTO(reservationRepository.save(updated));
    }

    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("Reservation not found");
        }
        reservationRepository.deleteById(id);
    }
}

