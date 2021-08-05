package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.dto.ClockinData;
import com.sekoding.example.demo.dto.ClockoutData;
import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.model.entity.Clock;
import com.sekoding.example.demo.services.ClockService;
import org.modelmapper.ModelMapper;
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
import java.util.TimeZone;

@RestController
@RequestMapping("api/")
public class ClockController {

    @Autowired
    private ClockService clockService;

    private ModelMapper modelMapper;

    private static String UPLOADED_PATH = "C:/Users/HP/Desktop/springHCM/img/";
//    private static String UPLOADED_PATH = "/home/adiabdurrakh/opt/sinarmas/demo/asset";

    @PostMapping("/clockin")
    public ResponseEntity<ResponseData<Clock>> clockin(@Valid @RequestParam("picture") MultipartFile picture, @ModelAttribute ClockinData clockinData, Errors errors) {

        ResponseData<Clock> responseData = new ResponseData<>();
        Clock clockinr = new Clock();
        Date date = new Date();

        try {
            byte[] bytes = picture.getBytes();
            Path path = Paths.get((UPLOADED_PATH) + date.getTime() + picture.getOriginalFilename());
            Files.write(path, bytes);
            clockinr.setUrl_foto_clockin(path.toString());
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
        clockinr.setStart_time(java.time.LocalDateTime.now());
        clockinr.setLocation_clockin(clockinData.getLocation_clockin());
        clockinr.setWorking(true);
        clockinr.setLevel_kesehatan_fisik_id(clockinData.getLevel_kesehatan_fisik_id());
        clockinr.setLevel_kesehatan_mental_Id(clockinData.getLevel_kesehatan_mental_Id());
        clockinr.setUser_id(clockinData.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(clockService.create(clockinr));
        return ResponseEntity.ok(responseData);
    }


    @PostMapping("/clockout")
    public ResponseEntity<ResponseData<Clock>> clockout(@Valid @RequestParam("picture") MultipartFile picture, @ModelAttribute ClockoutData clockoutData, Errors errors) {

        ResponseData<Clock> responseData = new ResponseData<>();
        Clock clockinr = new Clock();
        Date date = new Date();

        try {
            byte[] bytes = picture.getBytes();
            Path path = Paths.get((UPLOADED_PATH) + date.getTime() + picture.getOriginalFilename());
            Files.write(path, bytes);
            clockinr.setUrl_foto_clockout(path.toString());
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
        clockinr.setEnd_time(java.time.LocalDateTime.now());
        clockinr.setLocation_clockout(clockoutData.getLocation_clockout());
        clockinr.setWorking(false);
        clockinr.setLevel_kesehatan_fisik_id(clockoutData.getLevel_kesehatan_fisik_id());
        clockinr.setLevel_kesehatan_mental_Id(clockoutData.getLevel_kesehatan_mental_Id());
        clockinr.setUser_id(clockoutData.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(clockService.create(clockinr));
        return ResponseEntity.ok(responseData);
    }


    @PostMapping("/clocking")
    public ResponseEntity<ResponseData<Clock>> clocking(@Valid @RequestBody ClockinData clockinData, Errors errors) {
        ResponseData<Clock>responseData = new ResponseData<>();
        Clock clockinr = new Clock();
        Date date = new Date();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        clockinr.setStart_time(java.time.LocalDateTime.now());
        clockinr.setLocation_clockin(clockinData.getLocation_clockin());
        clockinr.setWorking(true);
        clockinr.setLevel_kesehatan_fisik_id(clockinData.getLevel_kesehatan_fisik_id());
        clockinr.setLevel_kesehatan_mental_Id(clockinData.getLevel_kesehatan_mental_Id());
        clockinr.setUser_id(clockinData.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(clockService.create(clockinr));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/clockoutg")
    public ResponseEntity<ResponseData<Clock>> clockoutg(@Valid @RequestBody ClockoutData clockoutData, Errors errors) {

        ResponseData<Clock>responseData = new ResponseData<>();
        Clock clockinr = new Clock();
        Date date = new Date();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        clockinr.setEnd_time(java.time.LocalDateTime.now());
        clockinr.setLocation_clockout(clockoutData.getLocation_clockout());
        clockinr.setWorking(false);
        clockinr.setLevel_kesehatan_fisik_id(clockoutData.getLevel_kesehatan_fisik_id());
        clockinr.setLevel_kesehatan_mental_Id(clockoutData.getLevel_kesehatan_mental_Id());
        clockinr.setUser_id(clockoutData.getUser_id());

        responseData.setStatus(true);
        responseData.setPayload(clockService.create(clockinr));
        return ResponseEntity.ok(responseData);
    }








    @GetMapping("/clockin")
    public Iterable<Clock> findAll() {
        return clockService.findAll();
    }

    @GetMapping("/time")
    public String getWaktu() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
        Date date = new Date();
        String test = date.toString();

        return test;
    }

    @GetMapping("/clockin/{id}")
    public Clock findOne(@PathVariable("id") Long id) {
        return clockService.findByid(id);
    }

    @PutMapping("/clockin")
    public Clock update(@RequestBody Clock clockin) {
        return clockService.create(clockin);
    }

    @DeleteMapping("/clockin/delete/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        clockService.removeOne(id);
    }


}


