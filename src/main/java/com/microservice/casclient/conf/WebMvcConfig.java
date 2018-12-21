package com.microservice.casclient.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Description  注册拦截器
 * @Author libing
 * @Date 2018/12/21
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /** 注册拦截器 **/
    //多个拦截器时 以此添加 执行顺序按添加顺序
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(getHandlerInterceptor());
        interceptorRegistration.addPathPatterns("/**");
//        interceptorRegistration.excludePathPatterns("/user/login");
    }

    @Bean
    public static HandlerInterceptor getHandlerInterceptor(){
        return new CustomInterceptor();
    }

    /** 视图跳转控制器 **/
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /** 静态资源处理 **/
    //由于继承WebMvcConfigurationSupport后会导致自动配置失效，所以这里要指定默认的静态资源的位置
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
