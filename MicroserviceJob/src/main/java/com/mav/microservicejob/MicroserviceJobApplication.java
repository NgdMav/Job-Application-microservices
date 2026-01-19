package com.mav.microservicejob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroserviceJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceJobApplication.class, args);
    }

}
