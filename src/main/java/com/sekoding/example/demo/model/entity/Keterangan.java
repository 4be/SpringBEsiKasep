package com.sekoding.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "tbl_keterangan")
public class Keterangan implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate start_date;

    private LocalDate end_date;

    @Column(length = 300)
    private String files;

    @Column(length = 300)
    private String description;

    @ManyToOne
    private User user_id;


}
