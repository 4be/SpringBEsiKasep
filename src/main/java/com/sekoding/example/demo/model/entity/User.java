package com.sekoding.example.demo.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "tbl_user")
public @Data class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 10)
    private Integer role;

    @Column(length = 20)
    private Integer nik;

}
