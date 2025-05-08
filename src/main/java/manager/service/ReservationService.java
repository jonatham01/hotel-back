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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public static List<LocalDate> getDatesBetween(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;

        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusDays(1);
        }

        return dates;
    }


    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {
        List<LocalDate> dates = getDatesBetween(
          dto.getReservationStartDate(),dto.getReservationEndDate()
        );
        List<Integer> disabledRoomIdList  = new ArrayList<>();
        List<RoomCategoryDisponibilityRoom> dontAvailableRooms = new ArrayList<>();
        try {
            //genero bucle para agregar todas las Room category disponibilities en una lista
            dates.stream().forEach( localDate -> {
                Optional<RoomCategoryDisponibility> disponibility = reservationCategoryDisponibilityRepository.findByRoomCategoryIdAAndDate(
                        dto.getReservationRoomCategoryId(),
                        localDate
                );
                if(disponibility.isPresent() ) {
                    dontAvailableRooms.addAll(disponibility.get().getDontAvailableRooms());
                }
            });
            //añadir todos los ids de los rooms, sino estan en la lista
            if(!dontAvailableRooms.isEmpty()) {
                dontAvailableRooms.forEach( roomCategoryDisponibilityRoom -> {
                    Integer roomId = roomCategoryDisponibilityRoom.getId().getRoomId();
                    if (!disabledRoomIdList.contains(roomId))  disabledRoomIdList.add(roomId);
                });
            }
            //find rooms and remove reserved ones
            List<Room> allRooms = roomRepository.findAllByRoomStatus("Enable");
           if(!disabledRoomIdList.isEmpty()) {
               allRooms.removeAll(disabledRoomIdList);
           }
           //disponibilities creation to disable reserved rooms
            String name = roomCategoryRepository.findById(dto.getReservationRoomCategoryId()).get().getRoomCategoryName();
            dates.forEach(localDate -> {
                Optional<RoomCategoryDisponibility> reservationCategoryDisponibility = reservationCategoryDisponibilityRepository.findByRoomCategoryIdAAndDate(
                        dto.getReservationRoomCategoryId(),
                        localDate
                );
                RoomCategoryDisponibility roomCategoryDisponibility;
                if(reservationCategoryDisponibility.isPresent()) {
                    roomCategoryDisponibility = new RoomCategoryDisponibility();
                    roomCategoryDisponibility.setCategoryId(dto.getReservationRoomCategoryId());
                    roomCategoryDisponibility.setCategoryName(name);
                    roomCategoryDisponibility.setDate(localDate);
                }else{
                    roomCategoryDisponibility = reservationCategoryDisponibility.get();
                }
                roomCategoryDisponibility.setQuantity(allRooms.size());
                roomCategoryDisponibility.setOccupancy(reservationCategoryDisponibility.get().getOccupancy()+1);
                roomCategoryDisponibility.setDisponibilityLevel(reservationCategoryDisponibility.get().getDisponibilityLevel()-1);
                RoomCategoryDisponibility savedDisponibility = reservationCategoryDisponibilityRepository.save(roomCategoryDisponibility);

                //create RoomCategoryDisponibilityRoom
                RoomCategoryDisponibilityRoomPK pk = new RoomCategoryDisponibilityRoomPK(
                        allRooms.getFirst().getRoomId(),
                        savedDisponibility.getId()
                );
                allRooms.remove(allRooms.getFirst());
                RoomCategoryDisponibilityRoom roomCategoryDisponibilityRoom =new RoomCategoryDisponibilityRoom();
                roomCategoryDisponibilityRoom.setId(pk);
                RoomCategoryDisponibilityRoom roomCategoryDisponibilityRoomResponse = roomCategoryDisponibilityRoomRepository.save(roomCategoryDisponibilityRoom);
            });

            //reservation creation
            Hotel hotel = hotelRepository.findById(dto.getReservationHotelId()).orElseThrow(()->new RuntimeException("Hotel not found"));
            Client client = clientRepository.findById(dto.getReservationClientId()).orElseThrow(()->new RuntimeException("Client not found"));
            Reservation reservation = reservationMapper.toEntity(dto);
            reservation.setReservationRoom(allRooms.getFirst());
            reservation.setReservationHotel(hotel);
            reservation.setReservationClient(client);
            reservation.setReservationPayment(null);
            Reservation savedReservation = reservationRepository.save(reservation);
            //payment creation
            Payment payment = new Payment();
            payment.setPaymentClientId(dto.getReservationClientId());
            payment.setPaymentTotalAmount(dto.getReservationTotalValue());
            payment.setPaymentDate(LocalDateTime.now());
            Payment savedPayment = paymentRepository.save(payment);
            //update paymentId
            reservation.setReservationPayment(savedPayment);
            Reservation updatedReservation= reservationRepository.save(reservation);
            //return
            return reservationMapper.toDTO(updatedReservation);

        }catch (Exception e){
            throw new EntityNotFoundException("Error while creating reservation");
        }

    }

    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReservationResponseDTO getReservationById(BigInteger id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }

    public ReservationResponseDTO updateReservation(BigInteger id, ReservationRequestDTO dto) {
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        Reservation updated = reservationMapper.toEntity(dto);
        updated.setReservationId(id); // Aseguramos que se actualiza el existente

        return reservationMapper.toDTO(reservationRepository.save(updated));
    }

    public void deleteReservation(BigInteger id) {
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("Reservation not found");
        }
        reservationRepository.deleteById(id);
    }
}

