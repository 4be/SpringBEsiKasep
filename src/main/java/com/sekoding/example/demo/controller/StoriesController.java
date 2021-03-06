package com.sekoding.example.demo.controller;


import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.dto.StoriesRequest;
import com.sekoding.example.demo.model.entity.Stories;
import com.sekoding.example.demo.services.StoriesServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/stories/")
public class StoriesController {


    @Autowired
    private StoriesServices storiesServices;

    @Autowired
    private ModelMapper modelMapper;

//        private static String UPLOADED_PATH = "/Users/HP/Desktop/springHCM/src/main/resources/static/images/";
    private static String UPLOADED_PATH = "/home/adiabdurrakh/opt/sinarmas/demo/public/img/";

    @PostMapping("/add")
    public ResponseEntity<ResponseData<Stories>> addstories(@RequestParam("picture") MultipartFile picture, @ModelAttribute StoriesRequest storiesRequest, Errors errors){
        ResponseData<Stories> responseData = new ResponseData<>();
        Stories stories = new Stories();
        Date date = new Date();

        try {
            if(picture.isEmpty()){
                stories.setUrl_foto_stories(null);
            }else {
                byte[] bytes = picture.getBytes();
                Path path = Paths.get((UPLOADED_PATH) + date.getTime() + picture.getOriginalFilename().replaceAll(" ","_"));
                Files.write(path, bytes);
                String urlImage = "35.209.242.226/img/" + date.getTime() + picture.getOriginalFilename().replaceAll(" ","_");
                stories.setUrl_foto_stories(urlImage);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        stories.setDate_published(java.time.LocalDateTime.now());
        stories.setDescription(storiesRequest.getDescription());
        stories.setUser_id(storiesRequest.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(storiesServices.create(stories));
        return ResponseEntity.ok((responseData));

    }

    @GetMapping("/list")
    public Iterable<Stories> findALl(){
        return storiesServices.findAll();

    }

    @GetMapping("/list/desc/")
    public List<Stories> findAllDesc(){
        return storiesServices.findAllDesc();
    }


    @GetMapping("/id/{id}")
    public Stories findStoriesId(@PathVariable("id") Long id){
        return storiesServices.findById(id);
    }

    @DeleteMapping("/delete/id/{id}")
    public void removeOne(@PathVariable("id") Long id){
        storiesServices.remmoveOne(id);
    }


}
