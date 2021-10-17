package com.example.roomseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RoomsEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomsEurekaServerApplication.class, args);
    }

}
