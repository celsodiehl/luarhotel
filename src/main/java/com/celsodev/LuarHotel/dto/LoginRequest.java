package com.celsodev.LuarHotel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email é obrigatório!")
    private String email;
    @NotBlank(message = "Password é obrigatório!")
    private String password;
}
