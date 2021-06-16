package com.jta.in.cws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommonWebservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonWebservicesApplication.class, args);
    }

}