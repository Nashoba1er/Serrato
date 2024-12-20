package com.emse.spring.serrato;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerratoApplicationConfig {

    @Bean
    public CommandLineRunner greetingCommandLine() {
        return args -> {
        };
    }
}