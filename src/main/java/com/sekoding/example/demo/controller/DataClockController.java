package com.sekoding.example.demo.controller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataClockController {

    private ModelMapper modelMapper;

//    @GetMapping("/dataclock")
//    public String index(Model model) {
//        model.addAttribute("title", "Data Clock");
//        return "dataClock/index";
//    }
}
