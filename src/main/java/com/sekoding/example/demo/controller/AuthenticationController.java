package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.request.SignInRequest;
import com.sekoding.example.demo.model.request.SignUpRequest;
import com.sekoding.example.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signupRequest) {
        Object data = authenticationService.signUpUser(signupRequest);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signinRequest) {
        Object data = authenticationService.signInUser(signinRequest);
        return ResponseEntity.ok(data);
    }

}
