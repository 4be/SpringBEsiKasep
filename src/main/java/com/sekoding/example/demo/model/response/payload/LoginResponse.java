package com.sekoding.example.demo.model.response.payload;

public class LoginResponse {

    private Long id;
    private String nik;
    private String alamat;
    private String tanggalLahir;
    private String email;
    private String role;
    private String token;

    public LoginResponse(Long id, String nik, String alamat, String tanggalLahir, String email, String role, String token) {
        this.id = id;
        this.nik = nik;
        this.alamat = alamat;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        this.role = role;
        this.token = token;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
