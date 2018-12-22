package com.microservice.casclient.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 自定义拦截器
 * 拦截器是被包裹在过滤器之中的
 * 过滤器、监听器都属于Servlet的api，我们在开发中处理利用以上的进行过滤web请求时，
 * 还可以使用Spring提供的拦截器(HandlerInterceptor)进行更加精细的控制。
 * @Author libing
 * @Date 2018/12/21
 **/
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    //preHandle()这个方法是在过滤器的chain.doFilter(request, response)方法的前一步执行，
    // 也就是在 System.out.println(“before…”)和chain.doFilter(request, response)之间执行的。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("customInterceptor preHandle: 请求前调用");
        return true;
    }

    //postHandle()方法在preHandle()方法之后，return ModelAndView之前执行，
    // 可以操控Controller的ModelAndView内容。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("customInterceptor postHandle: 请求后调用");
    }

//    afterCompletion()方法是在过滤器返回给前端的前一步执行，也就是在chain.doFilter(request, response)和System.out.println(“after…”)之间执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("customInterceptor afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }
}
