package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.ERole;
import com.sekoding.example.demo.model.entity.Role;
import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.model.request.SignInRequest;
import com.sekoding.example.demo.model.request.SignUpRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.SignInResponse;
import com.sekoding.example.demo.model.response.payload.SignUpResponse;
import com.sekoding.example.demo.repository.RoleRepository;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.security.jwt.JwtUtils;
import com.sekoding.example.demo.security.service.UserDetailsImpl;
import com.sekoding.example.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public Object signUpUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new FailedResponse(
                HttpStatus.BAD_REQUEST, "NIK sudah terdaftar!"
            );
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new FailedResponse(
                HttpStatus.BAD_REQUEST, "Email sudah terdaftar"
            );
        }

        User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(
                signUpRequest.getPassword()
            )
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles.isEmpty()) {
            FailedResponse response = new FailedResponse(
                HttpStatus.BAD_REQUEST, "Role tidak boleh kosong!"
            );
            return response;
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "HCM":
                        Role adminRole = roleRepository.findByNameRole(ERole.HCM);
                        roles.add(adminRole);
                        break;
                    case "MANAGER":
                        Role modRole = roleRepository.findByNameRole(ERole.MANAGER);
                        roles.add(modRole);
                        break;
                    default:
                        Role defRole = roleRepository.findByNameRole(ERole.EMPLOYEE);
                        roles.add(defRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                signUpRequest.getUsername(),
                signUpRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> rolesList = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        SignUpResponse signUpResponse = new SignUpResponse(
            userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), rolesList
        );

        return new SuccessResponse(HttpStatus.OK, "Success", signUpResponse);
    }

    public Object signInUser(SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            SignInResponse signInResponse = new SignInResponse(jwt);

            return new SuccessResponse(HttpStatus.OK, "Success", signInResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

}
