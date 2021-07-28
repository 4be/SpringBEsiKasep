package com.sekoding.example.demo.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "tbl_clock")
public @Data class Clockin implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 10)
        private Long user_id;

        private LocalDateTime start_time;

        private Date end_time;

        private Boolean working = false;

        @Column(length = 100)
        private String location_clockin;

        @Column(length = 100)
        private String location_clockout;

        @Column(length = 100)
        private String url_foto_clockin;

        @Column(length = 100)
        private String url_foto_clockout;

        @Column(length = 10)
        private Long level_kesehatan_fisik_id;

        @Column(length = 10)
        private Long level_kesehatan_mental_Id;

}


