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
}
