package com.sekoding.example.demo.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class KeteranganData {

    private String start_date;
    private String end_date;
    private MultipartFile files;
    private Long user_id;
    private String description;

}
