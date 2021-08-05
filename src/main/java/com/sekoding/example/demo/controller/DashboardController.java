package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hcms/")
    public String index(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/hcms/create")
    public String createUser(Model model) {
        model.addAttribute("title", "Tambah User");
        return "create_user";
    }

    @GetMapping("/hcms/update/{nik}")
    public String updateUser(Map<String, Object> model, @PathVariable String nik) {
        model.put("title", "Ubah User");
        User user = userRepository.findUserByNik(nik);
        model.put("user", user);
        return "ubah_user";
    }

    @GetMapping("/hcms/datauser")
    public String dataUser(Model model) {
        model.addAttribute("title", "Data User");
        return "data_user";
    }

    @GetMapping("/hcms/dataketerangan")
    public String dataKeterangan(Model model) {
        model.addAttribute("title", "Data Keterangan");
        return "dataKeterangan/index";
    }

    @GetMapping("/hcms/dataclock")
    public String dataClock(Model model) {
        model.addAttribute("title", "Data Clock");
        return "dataClock/index";
    }
}
