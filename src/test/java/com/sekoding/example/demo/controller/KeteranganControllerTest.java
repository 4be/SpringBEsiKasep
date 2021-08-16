package com.sekoding.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sekoding.example.demo.model.entity.Keterangan;
import com.sekoding.example.demo.model.repos.KeteranganRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KeteranganControllerTest {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeteranganRepo ketRepo;

    @Test
    public void testListKeterangan() throws Exception {
        List<Keterangan> listKet = new ArrayList();
        listKet.add(new Keterangan());
        listKet.add(new Keterangan());
        Mockito.when(ketRepo.findAll()).thenReturn(listKet);
        String url = "/api/keterangan/list";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
