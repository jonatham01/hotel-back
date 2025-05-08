create database hotel_reservation_db;

use hotel_reservation_db;
CREATE TABLE hotel (
                       hotel_id INT PRIMARY KEY AUTO_INCREMENT,
                       hotel_name VARCHAR(100),
                       hotel_address VARCHAR(255),
                       hotel_email VARCHAR(255),
                       hotel_city VARCHAR(100),
                       hotel_state VARCHAR(100),
                       hotel_country VARCHAR(100)
);

CREATE TABLE client (
                        id_number BIGINT PRIMARY KEY,
                        kind VARCHAR(50),
                        birth_date DATE,
                        type_identifier VARCHAR(20),
                        first_name VARCHAR(100),
                        last_name VARCHAR(100),
                        email VARCHAR(255),
                        age SMALLINT,
                        gender VARCHAR(10),
                        status VARCHAR(20),
                        origin_country VARCHAR(100),
                        live_country VARCHAR(100)
);
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

CREATE TABLE hotel_phone (
                             number VARCHAR(20) PRIMARY KEY,
                             phone_hotel_id INT NOT NULL,
                             FOREIGN KEY (phone_hotel_id) REFERENCES hotel(hotel_id)
);

CREATE TABLE room_categories (
                                 room_category_id INT PRIMARY KEY AUTO_INCREMENT,
                                 room_category_name VARCHAR(100),
                                 room_category_night_price DOUBLE,
                                 room_category_description VARCHAR(255),
                                 room_category_gallery BIGINT,
                                 room_category_hotel_id INT NOT NULL,
                                 FOREIGN KEY (room_category_hotel_id) REFERENCES hotel(hotel_id)
);

CREATE TABLE room (
                      room_id INT PRIMARY KEY AUTO_INCREMENT,
                      room_name VARCHAR(100),
                      room_description TEXT,
                      room_category_id INT,
                      room_category_hotel INT,
                      room_status VARCHAR(20),
                      FOREIGN KEY (room_category_id) REFERENCES room_categories(room_category_id),
                      FOREIGN KEY (room_category_hotel) REFERENCES hotel(hotel_id)
);

CREATE TABLE room_attribute (
                                room_attribute_id INT PRIMARY KEY AUTO_INCREMENT,
                                room_attribute_name VARCHAR(100) UNIQUE NOT NULL,
                                room_attribute_description TEXT NOT NULL,
                                room_attribute_photo VARCHAR(255) UNIQUE NOT NULL,
                                room_attribute_category_id INT,
                                FOREIGN KEY (room_attribute_category_id) REFERENCES room_categories(room_category_id)
);

CREATE TABLE room_gallery (
                              room_category_gallery_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              room_category_gallery_tittle VARCHAR(50) UNIQUE NOT NULL,
                              room_category_description VARCHAR(200) NOT NULL,
                              room_category_gallery_image VARCHAR(255) UNIQUE NOT NULL,
                              room_category_gallery_category_id INT,
                              FOREIGN KEY (room_category_gallery_category_id) REFERENCES room_categories(room_category_id)
);

CREATE TABLE room_category_disponibility (
                                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                             category_id INT,
                                             category_name VARCHAR(100),
                                             date DATE,
                                             quantity INT,
                                             occupancy INT,
                                             disponibility_level INT
);

CREATE TABLE room_category_disponibility_room (
                                                  room_id INT,
                                                  disponibility_id BIGINT,
                                                  PRIMARY KEY (room_id, disponibility_id),
                                                  FOREIGN KEY (room_id) REFERENCES room(room_id),
                                                  FOREIGN KEY (disponibility_id) REFERENCES room_category_disponibility(id)
);
CREATE TABLE payment (
                         payment_id BINARY(16) PRIMARY KEY,
                         payment_total_amount DECIMAL(12,2),
                         payment_date TIMESTAMP,
                         reservation_client_id BIGINT,
                         FOREIGN KEY (reservation_client_id) REFERENCES client(id_number)
);
CREATE TABLE reservation (
                             reservation_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             reservation_start_date DATE,
                             reservation_end_date DATE,
                             reservation_total_value DECIMAL(12,2),
                             reservation_checkin_status VARCHAR(50),
                             reservation_checkout_status VARCHAR(50),
                             reservation_payment_status VARCHAR(50),
                             reservation_hotel_id INT,
                             reservation_room_id INT,
                             reservation_payment_id UUID,
                             reservation_client_id BIGINT,
                             FOREIGN KEY (reservation_hotel_id) REFERENCES hotel(hotel_id),
                             FOREIGN KEY (reservation_room_id) REFERENCES room(room_id),
                             FOREIGN KEY (reservation_payment_id) REFERENCES payment(payment_id),
                             FOREIGN KEY (reservation_client_id) REFERENCES client(id_number)
);

CREATE TABLE reservation_guest (
                                   reservation_guest_reservation_id BIGINT,
                                   reservation_guest_guest_id BIGINT,
                                   PRIMARY KEY (reservation_guest_reservation_id, reservation_guest_guest_id),
                                   FOREIGN KEY (reservation_guest_reservation_id) REFERENCES reservation(reservation_id),
                                   FOREIGN KEY (reservation_guest_guest_id) REFERENCES guest(id_number)
);



CREATE TABLE payment_transaction (
                                     payment_transaction_id INT PRIMARY KEY AUTO_INCREMENT,
                                     payment_transaction_total DOUBLE,
                                     payment_transaction_kind VARCHAR(50),
                                     payment_transaction_datetime TIMESTAMP,
                                     payment_transaction_payment_id BINARY(16),
                                     FOREIGN KEY (payment_transaction_payment_id) REFERENCES payment(payment_id)
);

CREATE TABLE fee (
                     fee_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     fee_public_value DOUBLE,
                     fee_date DATE,
                     fee_increment_rate SMALLINT,
                     fee_category_id INT,
                     FOREIGN KEY (fee_category_id) REFERENCES room_categories(room_category_id)
);
CREATE TABLE auth_users (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            username VARCHAR(255) NOT NULL UNIQUE,
                            name VARCHAR(255),
                            password VARCHAR(255),
                            role VARCHAR(255),
                            PRIMARY KEY (id)
);

CREATE TABLE `jwt-token` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `token` VARCHAR(2048),
                             `expires` DATETIME,
                             `is_valid` BOOLEAN,
                             `user_id` BIGINT,
                             PRIMARY KEY (`id`),
                             CONSTRAINT `fk_jwt_token_user`
                                 FOREIGN KEY (`user_id`)
                                     REFERENCES `user`(`id`)
);







