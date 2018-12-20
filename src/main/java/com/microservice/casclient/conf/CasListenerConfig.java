package com.microservice.casclient.conf;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.EventListener;

/**
 * @Description  cas监听器配置
 * @Author libing
 * @Date 2018/11/23
 **/
@Configuration
public class CasListenerConfig {
    /**
     * 添加监听器
     * 注销sessionMappingStorage是靠SingleSignOutHttpSessionListener来实现的,
     * 当有session被销毁的时候,触发将sessionMappingStorage中对应sessionid中的数据删除。
     * 所以在配置单点登出的时候,一定要配置这个监听器,否则客户端很容易导致内存溢出的
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration(){
        ServletListenerRegistrationBean<EventListener> listenerRegistrationBean = new ServletListenerRegistrationBean<EventListener>();
        listenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        listenerRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return listenerRegistrationBean;
    }
}
