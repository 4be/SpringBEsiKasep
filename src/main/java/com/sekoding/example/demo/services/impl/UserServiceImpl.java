package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.model.request.UserRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.UserResponse;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Object getAllUser() {

        try {
            List<User> userList = userRepository.findAll();
            List<UserResponse> UserResponseList = new ArrayList<>();

            for (User user : userList) {
                UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getNik(),
                    user.getAlamat(),
                    user.getTanggalLahir(),
                    user.getEmail(),
                    user.getRole()
                );
                UserResponseList.add(userResponse);
            }

            return new SuccessResponse(HttpStatus.OK, "Success", UserResponseList);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.MULTI_STATUS, e.getMessage());
        }

    }

    @Override
    public Object createUser(UserRequest userRequest) {

        if (userRepository.existsByUsername(userRequest.getNik())) {
            return new FailedResponse(
                HttpStatus.BAD_REQUEST, "NIK sudah terdaftar!"
            );
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return new FailedResponse(
                HttpStatus.BAD_REQUEST, "Email sudah terdaftar"
            );
        }

        User user = new User(
            userRequest.getNik(),
            userRequest.getNik(),
            userRequest.getAlamat(),
            userRequest.getTanggalLahir(),
            userRequest.getEmail(),
            encoder.encode(
                userRequest.getPassword()
            ),
            userRequest.getRole()
        );

        User save = userRepository.save(user);

        UserResponse userResponse = new UserResponse(
            save.getId(),
            save.getUsername(),
            save.getAlamat(),
            save.getTanggalLahir(),
            save.getEmail(),
            save.getRole()
        );

        return new SuccessResponse(HttpStatus.OK, "Success", userResponse);
    }

    @Override
    public Object deleteUser(Long id) {
        try {
            User user = userRepository.findById(id).get();
            UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getNik(),
                user.getAlamat(),
                user.getTanggalLahir(),
                user.getEmail(),
                user.getRole()
            );
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK, "Deleted", userResponse);
            userRepository.deleteById(id);
            return successResponse;
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
