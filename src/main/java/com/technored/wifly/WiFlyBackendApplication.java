package com.technored.wifly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WiFlyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiFlyBackendApplication.class, args);
    }

}
