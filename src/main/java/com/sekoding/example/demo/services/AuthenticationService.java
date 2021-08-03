package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.request.SignInRequest;
import com.sekoding.example.demo.model.request.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    Object signUpUser(SignUpRequest signUpRequest);

    Object signInUser(SignInRequest signInRequest);

}
