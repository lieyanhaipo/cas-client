package com.microservice.casclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.rmi.runtime.Log;

@SpringBootApplication
public class CasClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasClientApplication.class, args);
    }
}
