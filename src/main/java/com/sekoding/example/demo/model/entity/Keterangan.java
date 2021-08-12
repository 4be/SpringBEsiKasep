package com.sekoding.example.demo.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
