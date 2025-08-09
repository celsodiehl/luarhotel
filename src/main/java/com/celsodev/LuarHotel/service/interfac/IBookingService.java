package com.celsodev.LuarHotel.service.interfac;

import com.celsodev.LuarHotel.dto.Response;
import com.celsodev.LuarHotel.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
