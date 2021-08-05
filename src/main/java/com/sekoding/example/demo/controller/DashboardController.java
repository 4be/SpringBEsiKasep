package com.sekoding.example.demo.controller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private ModelMapper modelMapper;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/dataketerangan")
    public String dataKeterangan(Model model) {
        model.addAttribute("title", "Data Keterangan");
        return "dataKeterangan/index";
    }

    @GetMapping("/dataclock")
    public String dataClock(Model model) {
        model.addAttribute("title", "Data Clock");
        return "dataClock/index";
    }
}
