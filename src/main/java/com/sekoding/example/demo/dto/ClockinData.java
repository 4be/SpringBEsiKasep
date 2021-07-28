package com.sekoding.example.demo.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ClockinData {
        private String start_time;
        private String location_clockin;
        private Long level_kesehatan_fisik_id;
        private Long level_kesehatan_mental_Id;
        private MultipartFile picture;



}
