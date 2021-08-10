package com.sekoding.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "tbl_stories")
public class Stories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String url_foto_stories;

    @Column(length = 300)
    private String description;

    private LocalDateTime date_published;

    @ManyToOne
    private User user_id;





}
