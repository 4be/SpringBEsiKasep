package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.ERole;
import com.sekoding.example.demo.model.entity.Role;
import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.model.request.UserRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.UserResponse;
import com.sekoding.example.demo.repository.RoleRepository;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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
                    user.getNama(),
                    user.getNik(),
                    user.getAlamat(),
                    user.getTanggalLahir(),
                    user.getEmail(),
                    user.getDivisi(),
                    user.getRoles().iterator().next().getRolename().toString()
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

        Set<Role> roles = new HashSet<>();
        switch (userRequest.getRole()) {
            case "HCM":
                Role adminRole = roleRepository.findByRolename(ERole.HCM);
                roles.add(adminRole);
                break;
            case "MANAGER":
                Role modRole = roleRepository.findByRolename(ERole.MANAGER);
                roles.add(modRole);
                break;
            default:
                Role defRole = roleRepository.findByRolename(ERole.EMPLOYEE);
                roles.add(defRole);
        }

        User user = new User(
            userRequest.getNik(),
            userRequest.getNama(),
            userRequest.getNik(),
            userRequest.getAlamat(),
            userRequest.getTanggalLahir(),
            userRequest.getEmail(),
            encoder.encode(
                userRequest.getPassword()
            ),
            userRequest.getDivisi(),
            roles
        );

        User save = userRepository.save(user);

        UserResponse userResponse = new UserResponse(
            save.getId(),
            save.getNama(),
            save.getNik(),
            save.getAlamat(),
            save.getTanggalLahir(),
            save.getEmail(),
            save.getDivisi(),
            user.getRoles().iterator().next().getRolename().toString()
        );

        return new SuccessResponse(HttpStatus.OK, "Success", userResponse);
    }

    @Override
    public Object getUserById(Long id) {
        try {
            User user = userRepository.findById(id).get();
            UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getNama(),
                user.getNik(),
                user.getAlamat(),
                user.getTanggalLahir(),
                user.getEmail(),
                user.getDivisi(),
                user.getRoles().iterator().next().getRolename().toString()
            );
            return new SuccessResponse(HttpStatus.OK, "Success", userResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object updateUserByNik(UserRequest userRequest, String nik) {
        try {
            User user = userRepository.findUserByNik(nik);

            Set<Role> roles = new HashSet<>();
            switch (userRequest.getRole()) {
                case "HCM":
                    Role adminRole = roleRepository.findByRolename(ERole.HCM);
                    roles.add(adminRole);
                    break;
                case "MANAGER":
                    Role modRole = roleRepository.findByRolename(ERole.MANAGER);
                    roles.add(modRole);
                    break;
                default:
                    Role defRole = roleRepository.findByRolename(ERole.EMPLOYEE);
                    roles.add(defRole);
            }

            user.setNama(userRequest.getNama());
            user.setAlamat(userRequest.getAlamat());
            user.setTanggalLahir(userRequest.getTanggalLahir());
            user.setEmail(userRequest.getEmail());
            user.setDivisi(userRequest.getDivisi());
            user.setRoles(roles);

            User save = userRepository.save(user);

            UserResponse userResponse = new UserResponse(
                save.getId(),
                user.getNama(),
                user.getNik(),
                user.getAlamat(),
                user.getTanggalLahir(),
                user.getEmail(),
                user.getDivisi(),
                user.getRoles().iterator().next().getRolename().toString()
            );
            return new SuccessResponse(HttpStatus.OK, "Updated", userResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return new SuccessResponse(HttpStatus.OK, "Deleted", "");
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object deleteUserByNik(String nik) {
        try {
            User user = userRepository.findUserByNik(nik);
            userRepository.deleteById(user.getId());
            return new SuccessResponse(HttpStatus.OK, "Deleted", "");
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
