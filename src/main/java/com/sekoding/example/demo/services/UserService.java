package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.request.UserRequest;

public interface UserService {

    Object getAllUser();

    Object createUser(UserRequest userRequest);

    Object deleteUser(Long id);

}
