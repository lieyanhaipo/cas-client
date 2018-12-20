package com.microservice.casclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import sun.rmi.runtime.Log;

@SpringBootApplication
@ServletComponentScan
@Slf4j
public class CasClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasClientApplication.class, args);
        log.info("casClient 服务启动");
        //测试日志级别
        log.debug("debug message");
        log.warn("warn message");
        log.info("info message");
        log.error("error message");
        log.trace("trace message");
    }
}
