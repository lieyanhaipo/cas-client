package com.microservice.casclient.conf;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description  cas过滤器配置
 * @Author libing
 * @Date 2018/11/23
 **/
//@Configuration
public class CasFilterConfig {
    private static final String CAS_SERVER_URL_PREFIX = "https://casserver.com:8443/";
    private static final String CAS_SERVER_URL_LOGIN = "https://casserver.com:8443/login";

    //本机的名称SingleSignOutFilter
    private static final String SERVER_NAME = "http://springbootcasclient.com:8001/";

    /**
     * description: 登录过滤器
     * SingleSignOutFilter ,主要是在有ticket参数的时候,将session放到sessionMappingStorage,
     * 如果参数中存在logoutRequest,则注销session,那么这个是在什么时候会触发呢,这个是在你登陆的任意客户端,
     * 调用https://xxx:8443/logout,这个取得cookie里面的TGT数据,找到TGT中关联的所有ST对应的地址,
     * 向每个地址方式一个http请求,并传递logoutRequest参数。
     * @param: []
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        //拦截路径
        registration.addUrlPatterns("/*");
        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", CAS_SERVER_URL_PREFIX);
        registration.setInitParameters(initParameters);
        //设置加载顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * description:过滤验证器
     *     * @param: []
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        //拦截路径
        registration.addUrlPatterns("/*");
        Map<String,String>  initParameters = new HashMap<String, String>();
        // cas服务地址，从后台请求CAS服务，得到ticket信息（内部通讯）
        initParameters.put("casServerUrlPrefix", CAS_SERVER_URL_PREFIX);
        initParameters.put("serverName", SERVER_NAME);
        initParameters.put("useSession", "true");
        registration.setInitParameters(initParameters);
        //设置加载顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * description:授权过滤器
     * @param: []
     * @return: org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String>  initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", CAS_SERVER_URL_LOGIN);
        initParameters.put("serverName", SERVER_NAME);
        //忽略/logout的路径
        initParameters.put("ignorePattern", "/logout/*");
        initParameters.put("ignoreUrlPatternType", "com.microservice.casclient.conf.UrlPatternMatcherStrategyImpl");

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * wraper过滤器
     * HttpServletRequestWrapperFilter其实作用很简单,就是在HttpServletRequest对象再包装一次,
     * 让其支持getUserPrincipal,getRemoteUser方法来取得登录的用户信息
     * @return
     */
    @Bean
    public FilterRegistrationBean filterWrapperRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

//    //注册自定义过滤器
//    @Bean
//    public FilterRegistrationBean  filterRegistrationBean() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        //当过滤器有注入其他bean类时，可直接通过@bean的方式进行实体类过滤器，这样不可自动注入过滤器使用的其他bean类。
//        //当然，若无其他bean需要获取时，可直接new CustomFilter()，也可使用getBean的方式。
//        registration.setFilter(customFilter());
//        //过滤器名称
//        registration.setName("customFilter");
//        //拦截路径
//        registration.addUrlPatterns("/*");
//        //设置顺序
//        registration.setOrder(10);
//        return registration;
//    }
//
//    @Bean
//    public Filter customFilter() {
//        return new CustomFilter();
//    }
}
