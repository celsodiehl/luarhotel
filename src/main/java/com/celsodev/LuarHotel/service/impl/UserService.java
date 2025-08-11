package com.celsodev.LuarHotel.service.impl;

import com.celsodev.LuarHotel.dto.LoginRequest;
import com.celsodev.LuarHotel.dto.Response;
import com.celsodev.LuarHotel.dto.UserDTO;
import com.celsodev.LuarHotel.entity.User;
import com.celsodev.LuarHotel.exception.OurException;
import com.celsodev.LuarHotel.repository.UserRepository;
import com.celsodev.LuarHotel.service.interfac.IUserService;
import com.celsodev.LuarHotel.utils.JWTUtils;
import com.celsodev.LuarHotel.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response register(User user) {
        Response response = new Response();
        try {
            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new OurException(user.getEmail() + "Já existe!");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser);
            response.setStatusCode(200);
            response.setUser(userDTO);
        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Ocorreu um erro durante o registro do usuário " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response = new Response();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("user Not found"));

            var token = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Dias");
            response.setMessage("Logado com sucesso!");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Ocorreu um erro durante o login do usuário " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllUsers() {

        Response response = new Response();
        try {
            List<User> userList = userRepository.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Erro ao buscar usuários! " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserBookingHistory(String userId) {

        Response response = new Response();
        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("Usuário não encontrado!"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Erro ao buscar usuários! " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {

        Response response = new Response();
        try {
            userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("Usuário não encontrado!"));
            userRepository.deleteById(Long.valueOf(userId));
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error ao buscar usuários! " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserById(String userId) {

        Response response = new Response();
        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("Usuário não encontrado!"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Erro ao buscar usuários! " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {

        Response response = new Response();
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new OurException("Usuário não encontrado!"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Erro ao buscar todos usuários!" + e.getMessage());
        }
        return response;
    }
}
