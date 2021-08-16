package com.sekoding.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sekoding.example.demo.model.entity.Keterangan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@SpringBootTest
@AutoConfigureMockMvc
class KeteranganControllerTest {

    @Autowired
    private MockMvc mockMvc;

    MA

    @Test
    public void testKeteranganMustNotBeBlank() throws JsonProcessingException,  Exception {
        Keterangan ket = new Keterangan();
        String url = "/api/keterangan/list";
        mockMvc.perform(post(url)
            .contentType("application/json")
            .content(objetMapper)
        );
    }
}
