package com.sekoding.example.demo.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ClockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/clockin/ mengambil semua data clockin ")
    void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/clockin/"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("GET /api/clockin/{id} mengambil data clock menggunakan id")
    void testFindByUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/api/clockin/{id}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }







}
