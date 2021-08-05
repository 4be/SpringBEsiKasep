package com.sekoding.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private ModelMapper modelMapper;

    @GetMapping("/hcms/")
    public String index(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/hcms/dataclock")
    public String index2(Model model) {
        model.addAttribute("title", "Data Clock");
        return "dataClock/index";
    }

    @GetMapping("/hcms/datasakit")
    public String dataSakit(Model model) {
        model.addAttribute("title", "Data Keterangan Sakit");
        return "data_sakit";
    }

    @GetMapping("/hcms/create")
    public String createUser(Model model) {
        model.addAttribute("title", "Tambah User");
        return "create_user";
    }

    @GetMapping("/hcms/datauser")
    public String dataUser(Model model) {
        model.addAttribute("title", "Data User");
        return "data_user";
    }
}
