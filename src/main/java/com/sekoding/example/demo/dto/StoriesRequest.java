package com.sekoding.example.demo.dto;

import com.sekoding.example.demo.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
public class StoriesRequest {

    private Long id;

    private MultipartFile picture;

    private String description;

    private String date_published;

    private User user_id;


}
