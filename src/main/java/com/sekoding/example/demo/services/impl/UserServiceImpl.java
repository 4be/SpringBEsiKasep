package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.UserResponse;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public Object getAllUser() {
        try {
            List<User> userList = userRepository.findAll();
            List<UserResponse> UserResponseList = new ArrayList<>();
            for (User user : userList) {

                UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRoles()
                );

                UserResponseList.add(userResponse);
            }
            SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Success", UserResponseList);
            return response;
        } catch (Exception e) {
            FailedResponse response = new FailedResponse(HttpStatus.MULTI_STATUS, e.getMessage());
            return response;
        }
    }
}
