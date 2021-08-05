package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.request.UserRequest;
import com.sekoding.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<Object> getAllUser() {
        Object data = userService.getAllUser();
        return ResponseEntity.ok(data);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest userRequest) {
        Object data = userService.createUser(userRequest);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Object data = userService.getUserById(id);
        return ResponseEntity.ok(data);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Object data = userService.deleteUser(id);
        return ResponseEntity.ok(data);
    }


}
