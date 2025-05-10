-- NO EJECUTAR ESTO EN LA BASE DE DATOS
CREATE TABLE guest (
                       id_number BIGINT PRIMARY KEY,
                       guest_kind VARCHAR(50),
                       guest_birthdate DATE,
                       guest_start_date DATE,
                       guest_end_date DATE,
                       guest_hotel_id INT,
                       FOREIGN KEY (id_number) REFERENCES client(id_number),
                       FOREIGN KEY (guest_hotel_id) REFERENCES hotel(hotel_id)
);
CREATE TABLE reservation_guest (
                                   reservation_guest_reservation_id BIGINT,
                                   reservation_guest_guest_id BIGINT,
                                   PRIMARY KEY (reservation_guest_reservation_id, reservation_guest_guest_id),
                                   FOREIGN KEY (reservation_guest_reservation_id) REFERENCES reservation(reservation_id),
                                   FOREIGN KEY (reservation_guest_guest_id) REFERENCES guest(id_number)
);