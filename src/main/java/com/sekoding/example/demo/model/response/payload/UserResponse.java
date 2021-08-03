package com.sekoding.example.demo.model.response.payload;

import com.sekoding.example.demo.model.entity.Role;

import java.util.Set;

public class UserResponse {

    private Long id;
    private String nik;
    private String alamat;
    private String tanggalLahir;
    private String email;
    private Set<Role> roles;

    public UserResponse(Long id, String nik, String alamat, String tanggalLahir, String email, Set<Role> roles) {
        this.id = id;
        this.nik = nik;
        this.alamat = alamat;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
