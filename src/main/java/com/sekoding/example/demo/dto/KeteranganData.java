package com.sekoding.example.demo.dto;


import com.sekoding.example.demo.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class KeteranganData {

    private String start_date;
    private String end_date;
    private MultipartFile files;
    private String description;
    private User user_id;

}
