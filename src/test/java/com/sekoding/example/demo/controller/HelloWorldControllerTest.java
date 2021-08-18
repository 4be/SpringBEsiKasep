package com.sekoding.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "EMPLOYEE")
    @DisplayName("GET / mengembalikan 'Hello, World'")
    public void testIndex_shouldReturn_HelloWorldPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
