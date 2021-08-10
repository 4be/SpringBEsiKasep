package com.sekoding.example.demo.model.request;

import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {

    private String nama;
    private String nik;
    private String alamat;
    private String tanggalLahir;
    private String email;
    private String password;
    private String divisi;
    private String nikManager;
    private String role;

}
