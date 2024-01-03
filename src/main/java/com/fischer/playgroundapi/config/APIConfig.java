package com.fischer.playgroundapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class APIConfig {

    @Bean
    public Random random() {
        return new Random();
    }
}
