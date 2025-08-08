package com.celsodev.LuarHotel.repository;

import com.celsodev.LuarHotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    //Busca tipos distintos de quartos
    @Query("SELECT DISTINCT r.type FROM Room r")
    List<String> findDistinctRoomTypes();

    //Busca quartos disponiveis por data e tipo
    @Query("SELECT r FROM Room r WHERE r.type LIKE %:type% AND r.id NOT IN (SELECT bk.room.id FROM Booking bk WHERE" +
            "(bk.checkInDate <= :checkOutDate) AND (bk.checkOutDate >= :checkInDate))")
    List<Room> findAvailableRoomsByDatesAndTypes(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

    //Busca todos quartos disponiveis
    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT b.room.id FROM Booking b)")
    List<Room> getAllAvailableRooms();

}
