package com.sekoding.example.demo.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_clock")
public @Data
class Clock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private Long user_id;

    private LocalDateTime start_time;

    private LocalDateTime end_time;

    private Boolean working = false;

    @Column(length = 100)
    private String location_clockin;

    @Column(length = 100)
    private String location_clockout;

    @Column(length = 300)
    private String url_foto_clockin;

    @Column(length = 300)
    private String url_foto_clockout;

    @Column(length = 10)
    private Long level_kesehatan_fisik_id;

    @Column(length = 10)
    private Long level_kesehatan_mental_Id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }

    public String getLocation_clockin() {
        return location_clockin;
    }

    public void setLocation_clockin(String location_clockin) {
        this.location_clockin = location_clockin;
    }

    public String getLocation_clockout() {
        return location_clockout;
    }

    public void setLocation_clockout(String location_clockout) {
        this.location_clockout = location_clockout;
    }

    public String getUrl_foto_clockin() {
        return url_foto_clockin;
    }

    public void setUrl_foto_clockin(String url_foto_clockin) {
        this.url_foto_clockin = url_foto_clockin;
    }

    public String getUrl_foto_clockout() {
        return url_foto_clockout;
    }

    public void setUrl_foto_clockout(String url_foto_clockout) {
        this.url_foto_clockout = url_foto_clockout;
    }

    public Long getLevel_kesehatan_fisik_id() {
        return level_kesehatan_fisik_id;
    }

    public void setLevel_kesehatan_fisik_id(Long level_kesehatan_fisik_id) {
        this.level_kesehatan_fisik_id = level_kesehatan_fisik_id;
    }

    public Long getLevel_kesehatan_mental_Id() {
        return level_kesehatan_mental_Id;
    }

    public void setLevel_kesehatan_mental_Id(Long level_kesehatan_mental_Id) {
        this.level_kesehatan_mental_Id = level_kesehatan_mental_Id;
    }
}


