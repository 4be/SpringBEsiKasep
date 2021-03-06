package com.sekoding.example.demo.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "tbl_clock")
public @Data
class Clock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime times;

    private Boolean working = false;

    @Column(length = 150)
    private String location_clock;

    @Column(length = 100)
    private String coordinate;

    @Column(length = 300)
    private String url_foto_clock;

    @Column(length = 10)
    private Long level_kesehatan_fisik_id;

    @Column(length = 10)
    private Long level_kesehatan_mental_Id;

    @ManyToOne
    private User user_id;

}


