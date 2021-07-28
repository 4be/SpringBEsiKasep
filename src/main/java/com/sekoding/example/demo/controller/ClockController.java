package com.sekoding.example.demo.controller;

import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import com.sekoding.example.demo.dto.ClockinData;
import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.model.entity.Clockin;
import com.sekoding.example.demo.services.ClockinService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/")
public class ClockController {

    @Autowired
    private ClockinService clockinService;

    private ModelMapper modelMapper;

    private static String UPLOADED_PATH = "C:/Users/HP/Desktop/springHCM/upload";

    @PostMapping("/clockin")
    public ResponseEntity<ResponseData<Clockin>> create(@Valid @RequestParam("picture") MultipartFile picture, @ModelAttribute ClockinData clockinData, Errors errors) {

        ResponseData<Clockin> responseData = new ResponseData<>();
        Clockin clockinr = new Clockin();

//        if(Files.notExists()){ // check kondisi
////            RedirectAttributes.addAtribute("Message","sucesss");
//        }
        try {
            byte[] bytes = picture.getBytes();
            Path path = Paths.get((UPLOADED_PATH)+picture.getOriginalFilename());
            Files.write(path,bytes);
//            RedirectAttributes.addAtribute("Message","sucesss"); // check kondisi berhasil
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

        responseData.setStatus(true);
        responseData.setPayload(clockinService.create(clockinr));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/clockin")
    public Iterable<Clockin> findAll(){
        return clockinService.findAll();
    }

    @GetMapping("/{id}")
    public Clockin findOne(@PathVariable("id") Long id){
        return clockinService.FindByid(id);
    }

    @PutMapping("/clockin")
    public Clockin update(@RequestBody Clockin clockin){
        return clockinService.create(clockin);
    }

    @DeleteMapping("/clockin/delete/{id}")
    public void removeOne(@PathVariable("id") Long id){
        clockinService.removeOne(id);
    }



}


