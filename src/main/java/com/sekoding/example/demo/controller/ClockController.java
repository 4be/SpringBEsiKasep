package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.entity.Clockin;
import com.sekoding.example.demo.services.ClockinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clockin")
public class ClockController {

    @Autowired
    private ClockinService clockinService;

    @PostMapping
    public Clockin create(@RequestBody Clockin clockin){
        return clockinService.create(clockin);
    }

    @GetMapping
    public Iterable<Clockin> findAll(){
        return clockinService.findAll();
    }

    public ClockController(ClockinService clockinService) {
        this.clockinService = clockinService;
    }

}


