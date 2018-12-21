package com.microservice.casclient.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description 自定义监听器
 * 监听器是servlet规范中定义的一种特殊类。用于监听servletContext、HttpSession和servletRequest等域对象的创建和销毁事件。
 * 监听域对象的属性发生修改的事件。用于在事件发生前、发生后做一些必要的处理。一般是获取在线人数等业务需求。
 * @Author libing
 * @Date 2018/12/21
 **/
@WebListener
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CustomListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("CustomListener: 销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("CustomListener: 初始化");
    }
}
