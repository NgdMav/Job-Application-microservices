package com.mav.microservicecompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCompanyApplication.class, args);
    }

}
