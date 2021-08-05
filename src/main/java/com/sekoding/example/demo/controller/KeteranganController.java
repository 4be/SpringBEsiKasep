package com.sekoding.example.demo.controller;


import com.sekoding.example.demo.dto.KeteranganData;
import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.model.entity.Keterangan;
import com.sekoding.example.demo.services.KeteranganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping("api/keterangan/")
public class KeteranganController {

    @Autowired
    private KeteranganService keteranganService;

    private static String UPLOADED_PATH = "C:/Users/HP/Desktop/springHCM/";
//    private static String UPLOADED_PATH = "/home/adiabdurrakh/opt/sinarmas/demo/asset/";

    @PostMapping("/add")
    public ResponseEntity<ResponseData<Keterangan>> addket(@Valid @RequestParam("files") MultipartFile files, @ModelAttribute KeteranganData keteranganData, Errors errors) {

        ResponseData<Keterangan>responseData = new ResponseData<>();
        Keterangan keterangan = new Keterangan();
        Date date = new Date();

        try {
            byte[] bytes = files.getBytes();
            Path path = Paths.get((UPLOADED_PATH) + files.getOriginalFilename());
            Files.write(path,bytes);
            keterangan.setFiles(path.toString());
        } catch (IOException ex) {
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

        keterangan.setStart_date(java.time.LocalDate.parse(keteranganData.getStart_date()));
        keterangan.setEnd_date(java.time.LocalDate.parse(keteranganData.getEnd_date()));
        keterangan.setDescription(keteranganData.getDescription());
        keterangan.setUser_id(keteranganData.getUser_id());


        responseData.setStatus(true);
        responseData.setPayload(keteranganService.create(keterangan));
        return ResponseEntity.ok((responseData));
    }

    @GetMapping("/list")
    public Iterable<Keterangan> findAll(){
        return keteranganService.findAll();
    }

    @GetMapping("/list/id/{id}")
    public Keterangan findOne(@PathVariable("id") Long id){
        return keteranganService.findByid(id);
    }

    @DeleteMapping("/delete/id/{id}")
    public void removeOne(@PathVariable("id") Long id){
        keteranganService.removeOne(id);
    }








}
