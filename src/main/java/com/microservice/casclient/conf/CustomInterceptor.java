package com.microservice.casclient.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 自定义拦截器
 * 过滤器、监听器都属于Servlet的api，我们在开发中处理利用以上的进行过滤web请求时，
 * 还可以使用Spring提供的拦截器(HandlerInterceptor)进行更加精细的控制。
 * @Author libing
 * @Date 2018/12/21
 **/
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("customInterceptor preHandle: 请求前调用");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("customInterceptor postHandle: 请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("customInterceptor afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }
}
