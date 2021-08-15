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
import java.util.List;

@RestController
@RequestMapping("api/keterangan/")
public class KeteranganController {

    @Autowired
    private KeteranganService keteranganService;

//    private static String UPLOADED_PATH = "/Users/HP/Desktop/springHCM/src/main/resources/static/images/";
    private static String UPLOADED_PATH = "/home/adiabdurrakh/opt/sinarmas/demo/public/img/";

    @PostMapping("/add")
    public ResponseEntity<ResponseData<Keterangan>> addket(@Valid @RequestParam("files") MultipartFile files, @ModelAttribute KeteranganData keteranganData, Errors errors) {

        ResponseData<Keterangan> responseData = new ResponseData<>();
        Keterangan keterangan = new Keterangan();
        Date date = new Date();

        try {
            byte[] bytes = files.getBytes();
            Path path = Paths.get((UPLOADED_PATH) + date.getTime() + files.getOriginalFilename().replaceAll(" ","_"));
            Files.write(path, bytes);
            String urlImage = "35.209.242.226/img/" + date.getTime() + files.getOriginalFilename().replaceAll(" ","_");
            keterangan.setFiles(urlImage);
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
    public Iterable<Keterangan> findAll() {
        return keteranganService.findAll();
    }

    @GetMapping("/list/id/{id}")
    public Keterangan findOne(@PathVariable("id") Long id) {
        return keteranganService.findByid(id);
    }

    @DeleteMapping("/delete/id/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        keteranganService.removeOne(id);
    }

    @GetMapping("/list/desc/{id}")
    public List<Keterangan> getKetDesc(@PathVariable("id") Long id){
        return keteranganService.findKesByIdDesc(id);
    }

    @GetMapping("/team/{team}")
    public List<Keterangan> getKetTeam(@PathVariable("team")String team){
        return keteranganService.findKesByTeam(team);
    }

    @GetMapping("/total/month/{month}")
    public Iterable<String> getTotalKesMonth(@PathVariable("month")Long month){
        return keteranganService.findTotalKesMonth(month);
    }

}
