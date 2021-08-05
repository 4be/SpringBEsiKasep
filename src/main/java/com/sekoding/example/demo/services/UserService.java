package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.request.UserRequest;

public interface UserService {

    Object getAllUser();

    Object createUser(UserRequest userRequest);

    Object getUserById(Long id);

    Object updateUserById(UserRequest userRequest, Long id);

    Object deleteUser(Long id);

    Object deleteUserByNik(String nik);

}
