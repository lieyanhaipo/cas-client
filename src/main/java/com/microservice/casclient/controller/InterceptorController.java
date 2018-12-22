package com.microservice.casclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author libing
 * @Date 2018/12/22
 **/
@RestController
@RequestMapping("/inter")
@Slf4j
public class InterceptorController {
    @RequestMapping("/test")
    public String test(HttpServletResponse response) throws IOException {
        log.info("/inter/test控制器返回页面before...");
        return "<h1>Hello interceptor test</h1>";
    }
}
