package com.sekoding.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.sekoding.example.demo.controller.HelloWorldController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private HelloWorldController helloWorldController;

    @Test
    @DisplayName("Autowiring beans berfungsi")
    void contextLoads() {
        assertThat(helloWorldController).isNotNull();
    }

}
