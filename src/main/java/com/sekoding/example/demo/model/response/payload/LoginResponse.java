package com.sekoding.example.demo.model.response.payload;

import com.sekoding.example.demo.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class LoginResponse {

    private Long id;
    private String nama;
    private String nik;
    private String alamat;
    private String tanggalLahir;
    private String email;
    private Set<Role> roles;
    private String token;

}
