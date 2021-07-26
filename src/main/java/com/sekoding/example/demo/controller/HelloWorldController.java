package com.sekoding.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping(path = "/")
    public String index() {
        return "hello";
    }
}
