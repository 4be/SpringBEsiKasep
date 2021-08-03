package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.model.request.LoginRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.LoginResponse;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.security.jwt.JwtUtils;
import com.sekoding.example.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Override
    public Object loginUser(LoginRequest loginRequest) {
        try {
            if (userRepository.existsByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getUsername())) {
                User user = userRepository.findByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getUsername());
                String jwt = jwtUtils.generateJwtToken(user);
                LoginResponse loginResponse = new LoginResponse(
                    user.getId(),
                    user.getNik(),
                    user.getAlamat(),
                    user.getTanggalLahir(),
                    user.getEmail(),
                    user.getRole(),
                    jwt);
                return new SuccessResponse(HttpStatus.OK, "Success", loginResponse);
            } else {
                return new FailedResponse(HttpStatus.NOT_FOUND, "User tidak ditemukan");
            }
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

}
