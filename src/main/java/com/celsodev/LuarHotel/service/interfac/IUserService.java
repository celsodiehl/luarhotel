package com.celsodev.LuarHotel.service.interfac;

import com.celsodev.LuarHotel.dto.LoginRequest;
import com.celsodev.LuarHotel.dto.Response;
import com.celsodev.LuarHotel.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
