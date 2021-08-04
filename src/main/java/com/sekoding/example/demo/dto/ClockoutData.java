package com.sekoding.example.demo.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ClockoutData {
    private String end_time;
    private String location_clockout;
    private Long level_kesehatan_fisik_id;
    private Long level_kesehatan_mental_Id;
    private MultipartFile picture;
    private Long user_id;

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation_clockout() {
        return location_clockout;
    }

    public void setLocation_clockout(String location_clockout) {
        this.location_clockout = location_clockout;
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

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
