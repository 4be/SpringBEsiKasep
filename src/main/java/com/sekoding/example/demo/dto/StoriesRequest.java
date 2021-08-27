package com.sekoding.example.demo.dto;

import com.sekoding.example.demo.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;



@Getter
@Setter
@NoArgsConstructor
public class StoriesRequest {

    @Autowired
    private Long id;

    private MultipartFile picture;

    private String description;

    private User user_id;

}
