package com.sekoding.example.demo.dto;

import com.sekoding.example.demo.model.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;



@Getter
@Setter
public class StoriesRequest {

    private Long id;

    private MultipartFile picture;

    private String description;

    private User user_id;


}
