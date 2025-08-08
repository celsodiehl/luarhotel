package com.celsodev.LuarHotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    private Long id;
    private String type;
    private BigDecimal price;
    private String photoUrl;
    private String description;
    private List<BookingDTO> bookings;

}
