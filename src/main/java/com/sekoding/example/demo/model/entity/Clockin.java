package com.sekoding.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "tbl_clockin")
public @Data class Clockin implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(length = 10)
        private Long user_id;

        private Date waktu;

        @Column(length = 100)
        private String lokasi;

        @Column(length = 100)
        private String url_foto;

        @Column(length = 10)
        private Long level_id;

        @Column(length = 10)
        private Long status;

}


