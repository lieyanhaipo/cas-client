package com.microservice.casclient.conf;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description 自定义过滤器
 * 过滤器Filter，是Servlet的的一个实用技术了。可通过过滤器，对请求进行拦截，
 * 比如读取session判断用户是否登录、判断访问的请求URL是否有访问权限(黑白名单)等。主要还是可对请求进行预处理
 * @Author libing
 * @Date 2018/12/20
 **/
@WebFilter(filterName = "customFilter", urlPatterns = {"/*"}) //@WebFilter时Servlet3.0新增的注解，原先实现过滤器，需要在web.xml中进行配置，而现在通过此注解，启动启动时会自动扫描自动注册。
@Order(Ordered.HIGHEST_PRECEDENCE)   // 设置加载顺序
@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("customFilter: 请求处理");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("customFilter: 初始化");
    }

    @Override
    public void destroy() {
        log.info("customFilter: 销毁");
    }
}
