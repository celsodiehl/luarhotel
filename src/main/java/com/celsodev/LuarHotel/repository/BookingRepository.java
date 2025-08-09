package com.celsodev.LuarHotel.repository;

import com.celsodev.LuarHotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    //Busca por Id de quarto
    List<Booking> findByRoomId(Long roomId);

    //Busca por código de confirmação de reserva
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

    //Busca por Id de usuário
    List<Booking> findByUserId(Long userId);

}
