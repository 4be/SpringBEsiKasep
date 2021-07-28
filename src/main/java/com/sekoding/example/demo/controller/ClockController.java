package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.dto.ResponseData;
import com.sekoding.example.demo.model.entity.Clockin;
import com.sekoding.example.demo.services.ClockinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class ClockController {

    @Autowired
    private ClockinService clockinService;

    @PostMapping("/clockin")
    public ResponseEntity<ResponseData<Clockin>> create(@Valid @RequestBody Clockin clockin, Errors errors) {

        ResponseData<Clockin> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(clockinService.create(clockin));
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


